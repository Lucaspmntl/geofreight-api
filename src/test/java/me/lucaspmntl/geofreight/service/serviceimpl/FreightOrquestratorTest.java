package me.lucaspmntl.geofreight.service.serviceimpl;

import me.lucaspmntl.geofreight.dto.AddressDTO;
import me.lucaspmntl.geofreight.dto.NominatimDTO;
import me.lucaspmntl.geofreight.dto.brasilapi.BrasilApiDTO;
import me.lucaspmntl.geofreight.dto.brasilapi.CoordinatesDTO;
import me.lucaspmntl.geofreight.dto.brasilapi.LocationDTO;
import me.lucaspmntl.geofreight.model.Address;
import me.lucaspmntl.geofreight.service.BrasilApiService;
import me.lucaspmntl.geofreight.service.NominatimService;
import me.lucaspmntl.geofreight.service.OsrmService;
import me.lucaspmntl.geofreight.service.ViaCepService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FreightOrquestratorTest {

    @Mock
    BrasilApiService brasilApiService;

    @Mock
    NominatimService nominatimService;

    @Mock
    ViaCepService viaCepService;

    @Mock
    OsrmService osrmService;

    @InjectMocks
    FreightOrquestrator orquestrator;


    @Test
    @DisplayName("Deve chamar Nominatim quando as coordenadas não estão disponíveis em BrasilAPI")
    void case1() {

        String cep = "68902861";

        BrasilApiDTO brasilApiResponse = new BrasilApiDTO(
                                            new LocationDTO("type",
                                                    new CoordinatesDTO(null, null)));

        when(brasilApiService.getCoordinatesByCep(cep))
                .thenReturn(brasilApiResponse);

        when(nominatimService.getCoordinatesByAddress(cep))
                .thenReturn(List.of(new CoordinatesDTO(
                        -12.97333333, -38.5333333)));

        when(viaCepService.getAddressByCep(cep))
                .thenReturn(AddressDTO
                        .fromEntity(new Address()));

        AddressDTO result = orquestrator.getAddress(cep);

        assertNotNull(result);
        assertNotNull(result.coordinates());
        assertEquals(-12.97333333, result.coordinates().getLongitude());
        assertEquals(-38.5333333, result.coordinates().getLatitude());

        verify(nominatimService, times(1)).getCoordinatesByAddress(cep);
    }

    @Test
    @DisplayName("Não deve chamar nominatim quando BrasilAPI retornar as coordenadas")
    void case2(){
        String cep = "68902861";

        BrasilApiDTO brasilApiResponse = new BrasilApiDTO(
                new LocationDTO("type",
                        new CoordinatesDTO(-12.97333333, -38.5333333)));

        when(viaCepService.getAddressByCep(cep))
                .thenReturn(AddressDTO
                        .fromEntity(new Address()));

        when(brasilApiService.getCoordinatesByCep(cep))
                .thenReturn(brasilApiResponse);

        AddressDTO result = orquestrator.getAddress(cep);

        assertNotNull(result);
        assertNotNull(result.coordinates());
        assertEquals(-12.97333333, result.coordinates().getLongitude());
        assertEquals(-38.5333333, result.coordinates().getLatitude());
        verify(nominatimService, times(0)).getCoordinatesByAddress(cep);
    }

}
