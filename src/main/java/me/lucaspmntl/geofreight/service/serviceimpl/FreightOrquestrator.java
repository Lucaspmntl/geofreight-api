package me.lucaspmntl.geofreight.service.serviceimpl;

import me.lucaspmntl.geofreight.dto.AddressDTO;
import me.lucaspmntl.geofreight.dto.GeoFreightResponseDTO;
import me.lucaspmntl.geofreight.dto.brasilapi.CoordinatesDTO;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.MelhorEnvioRequestDTO;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.Product;
import me.lucaspmntl.geofreight.dto.melhorenvio.response.MelhorEnvioResponseDTO;
import me.lucaspmntl.geofreight.dto.osrm.OsrmDistanceDTO;
import me.lucaspmntl.geofreight.dto.osrm.RouteDTO;
import me.lucaspmntl.geofreight.model.Coordinates;
import me.lucaspmntl.geofreight.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class FreightOrquestrator {

    @Autowired
    BrasilApiService brasilApiService;

    @Autowired
    ViaCepService viaCepService;

    @Autowired
    NominatimService nominatimService;

    @Autowired
    MelhorEnvioService melhorEnvioService;

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


    public List<GeoFreightResponseDTO> getFreightsOptions(String cepOrigin, String cepDestination, MelhorEnvioRequestDTO dto) {

        double ferryCost = ferryPriceCalculator(dto.products());
        int ferryDays = 2;

        List<MelhorEnvioResponseDTO> response = melhorEnvioService.getFreights(cepOrigin, cepDestination, dto);

        return response.stream()
                .map(MelhorEnvioResponseDTO -> new GeoFreightResponseDTO(
                        MelhorEnvioResponseDTO.transportName(),
                        MelhorEnvioResponseDTO.transportCompanyPrice(),
                        ferryCost,
                        MelhorEnvioResponseDTO.transportCompanyPrice() + ferryCost,
                        MelhorEnvioResponseDTO.deliveryTime() + ferryDays,
                        MelhorEnvioResponseDTO.company()
                )).toList();
    }


    public double ferryPriceCalculator(List<Product> products){
        double totalWeight = products.stream()
                .mapToDouble(Product::weight)
                .sum();

        double declaredValue = products.stream()
                .mapToDouble(Product::insuranceValue)
                .sum();

        double dispatchTariff = 35.00;
        double custPerKg = totalWeight * 2.50;
        double adValorem = declaredValue * 0.01;

        return (dispatchTariff + custPerKg + adValorem);
    }

}
