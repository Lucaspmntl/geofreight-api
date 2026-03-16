package me.lucaspmntl.geofreight.service.serviceimpl;

import me.lucaspmntl.geofreight.dto.AddressDTO;
import me.lucaspmntl.geofreight.dto.GeoFreightRequestDTO;
import me.lucaspmntl.geofreight.dto.GeoFreightResponseDTO;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.From;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.MelhorEnvioRequestDTO;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.Product;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.To;
import me.lucaspmntl.geofreight.dto.melhorenvio.response.CompanyDTO;
import me.lucaspmntl.geofreight.dto.melhorenvio.response.MelhorEnvioResponseDTO;
import me.lucaspmntl.geofreight.exception.AmapaToAmapaException;
import me.lucaspmntl.geofreight.exception.NonAmapaAddresException;
import me.lucaspmntl.geofreight.service.MelhorEnvioService;
import me.lucaspmntl.geofreight.service.ViaCepService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class FreightOrquestratorTest {

    @Mock
    ViaCepService viaCepService;

    @Mock
    MelhorEnvioService melhorEnvioService;

    @InjectMocks
    FreightOrquestrator orquestrator;

    @Test
    @DisplayName("Deve retornar a lista de opções de fretes com custo de balsa")
    void case1() {

        when(viaCepService.getAddressByCep("76873228"))
                .thenReturn(new AddressDTO("76873-228", "Rua Uirapuru", "de 1513/1514 a 1974/1975",
                        "", "Setor 02", "Ariquemes", "Ro", "Rondônia", "Norte"));

        when(viaCepService.getAddressByCep("68901092"))
                .thenReturn(new AddressDTO("68901-092", "Avenida Doutor Acelino de Leão", "até 1248/1249",
                        "", "Trem", "Macapá", "AP", "Amapá", "Norte"));

        MelhorEnvioRequestDTO melhorEnvioRequest = new MelhorEnvioRequestDTO(
                new From("76873228"),
                new To("68901092"),
                List.of(new Product(10, 20, 30, 5.0, 50, 1))
        );

        when(melhorEnvioService.getFreights(any(), any(), eq(melhorEnvioRequest)))
                .thenReturn(List.of(
                        new MelhorEnvioResponseDTO(
                        "Pac",
                        10.0,
                        20,
                        new CompanyDTO("Correios")
                ),
                        new MelhorEnvioResponseDTO("Jadlog",
                                20.0,
                                30,
                                new CompanyDTO("Jadlog"))));

        GeoFreightRequestDTO externalRequest = new GeoFreightRequestDTO(
                "76873228",
                "68901092",
                List.of(new Product(10, 20, 30, 5.0, 50, 1))
        );


        List<GeoFreightResponseDTO> response = orquestrator.getFreightsOptions(externalRequest);

        assertNotNull(response);
        assertEquals(2, response.size());

        assertEquals("Pac", response.get(0).transportName());
        assertEquals(10.0, response.get(0).transportCompanyPrice());
        assertEquals(22, response.get(0).deliveryTime());
        assertEquals(48, response.get(0).ferryPrice());
        assertEquals(58, response.get(0).totalPrice());
        assertEquals(new CompanyDTO("Correios"), response.get(0).company());

        assertEquals("Jadlog", response.get(1).transportName());
        assertEquals(20.0, response.get(1).transportCompanyPrice());
        assertEquals(48, response.get(1).ferryPrice());
        assertEquals(68, response.get(1).totalPrice());
        assertEquals(32, response.get(1).deliveryTime());
        assertEquals(new CompanyDTO("Jadlog"), response.get(1).company());
    }

    @Test
    @DisplayName("Deve lançar AmapaToAmapaException quando o cep de origem e destino são do Amapá")
    void case2() {

        when(viaCepService.getAddressByCep("689382864"))
                .thenReturn(new AddressDTO("76873-228", "Cora de carvalho", "",
                        "", "Centro", "Macapá", "AP", "Amapá", "Norte"));

        when(viaCepService.getAddressByCep("68901092"))
                .thenReturn(new AddressDTO("68901-092", "Avenida Doutor Acelino de Leão", "até 1248/1249",
                        "", "Trem", "Macapá", "AP", "Amapá", "Norte"));

        GeoFreightRequestDTO externalRequest = new GeoFreightRequestDTO(
                "689382864",
                "68901092",
                List.of(new Product(10, 20, 30, 5.0, 50, 1))
        );

        assertThrows(AmapaToAmapaException.class, () -> orquestrator.getFreightsOptions(externalRequest));
    }

    @Test
    @DisplayName("Deve lançar NonAmapaException quando o cep de origem e destino não são do Amapá")
    void case3() {

        when(viaCepService.getAddressByCep("76873228"))
                .thenReturn(new AddressDTO("76873-228", "Rua Uirapuru", "de 1513/1514 a 1974/1975",
                        "", "Setor 02", "Ariquemes", "Ro", "Rondônia", "Norte"));

        when(viaCepService.getAddressByCep("47948783"))
                .thenReturn(new AddressDTO("47948-783", "Avenida Brasil", "até 1248/1249",
                        "", "Centro", "São Paulo", "sp", "São Paulo", "Centro-Oeste"));

        GeoFreightRequestDTO externalRequest = new GeoFreightRequestDTO(
                "76873228",
                "47948783",
                List.of(new Product(10, 20, 30, 5.0, 50, 1))
        );

        assertThrows(NonAmapaAddresException.class, () -> orquestrator.getFreightsOptions(externalRequest));
    }
}
