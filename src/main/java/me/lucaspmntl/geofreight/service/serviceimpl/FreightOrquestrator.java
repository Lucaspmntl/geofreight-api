package me.lucaspmntl.geofreight.service.serviceimpl;

import me.lucaspmntl.geofreight.dto.AddressDTO;
import me.lucaspmntl.geofreight.dto.brasilapi.CoordinatesDTO;
import me.lucaspmntl.geofreight.model.Coordinates;
import me.lucaspmntl.geofreight.service.BrasilApiService;
import me.lucaspmntl.geofreight.service.NominatimService;
import me.lucaspmntl.geofreight.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreightOrquestrator {

    @Autowired
    BrasilApiService brasilApiService;

    @Autowired
    ViaCepService viaCepService;

    @Autowired
    NominatimService nominatimService;

    public AddressDTO getAddress(String cep){
        AddressDTO address = viaCepService.getAddressByCep(cep);

        CoordinatesDTO coordinates = brasilApiService
                .getCoordinatesByCep(cep)
                .location()
                .coordinates();

        if (coordinates.latitude() == null || coordinates.longitude() == null){

            coordinates = nominatimService.getCoordinatesByAddress(cep).getFirst();
        }

        return new AddressDTO(
                address.cep(),
                address.street(),
                address.complement(),
                address.unit(),
                address.neighborhood(),
                address.city(),
                address.region(),
                address.uf(),
                address.state(),
                address.ibge(),
                address.ddd(),
                address.gia(),
                address.siafi(),
                new Coordinates(coordinates.longitude(), coordinates.latitude())
        );
    }
}
