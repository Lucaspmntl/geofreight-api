package me.lucaspmntl.geofreight.service.serviceimpl;

import me.lucaspmntl.geofreight.dto.AddressDTO;
import me.lucaspmntl.geofreight.dto.GeoFreightRequestDTO;
import me.lucaspmntl.geofreight.dto.GeoFreightResponseDTO;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.From;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.MelhorEnvioRequestDTO;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.Product;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.To;
import me.lucaspmntl.geofreight.dto.melhorenvio.response.MelhorEnvioResponseDTO;
import me.lucaspmntl.geofreight.exception.*;
import me.lucaspmntl.geofreight.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreightOrquestrator {

    @Value("${melhorenvio.token}")
    String token;

    @Value("${melhorenvio.email}")
    String email;

    @Autowired
    ViaCepService viaCepService;

    @Autowired
    MelhorEnvioService melhorEnvioService;

    public List<GeoFreightResponseDTO> getFreightsOptions(GeoFreightRequestDTO dto) {

        MelhorEnvioRequestDTO melhorEnvioDto = new MelhorEnvioRequestDTO(
                new From(dto.cepOrigin()),
                new To(dto.cepDestination()),
                dto.products()
        );

        double ferryCost = ferryPriceCalculator(dto.products());
        int ferryDays = 2;

        addressValidator(dto.cepOrigin(), dto.cepDestination());

        List<MelhorEnvioResponseDTO> response = melhorEnvioService
                .getFreights(token, email, melhorEnvioDto);

        return response.stream()
                .map(obj -> new GeoFreightResponseDTO(
                        obj.transportName(),
                        obj.transportCompanyPrice(),
                        ferryCost,
                        obj.transportCompanyPrice() + ferryCost,
                        obj.deliveryTime() + ferryDays,
                        obj.company()
                )).toList();
    }



    private double ferryPriceCalculator(List<Product> products){
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



    private void addressValidator(String cepOrigin, String cepDestination){

        AddressDTO originAddress = getCepWithContext(cepOrigin,
                "O CEP de origem informado é inválido ou não foi encontrado");

        AddressDTO destinationAddress = getCepWithContext(cepDestination,
                "O CEP de destino informado é inválido ou não foi encontrado");

        boolean notToAmapa = !originAddress.uf().equalsIgnoreCase("AP")
                && !destinationAddress.uf().equalsIgnoreCase("AP");

        boolean amapaToAmapa = originAddress.uf().equalsIgnoreCase("AP")
                && destinationAddress.uf().equalsIgnoreCase("AP");

        if (notToAmapa)
            throw new NonAmapaAddresException("A rota informada é inválida: \n" +
                    "O serviço logístico exige que a origem ou o destino pertença ao estado do Amapá (AP).!");

        if (amapaToAmapa) {
            throw new AmapaToAmapaException("A rota informada é inválida: \n" +
                    "Rotas de transporte logístico interno (origem e destino dentro do Amapá)" +
                    " não são suportadas por esta modalidade.");
        }
    }




    private AddressDTO getCepWithContext(String cep, String erroMessage){
        try{
            return viaCepService.getAddressByCep(cep);
        } catch (ExternalIntegrationException e) {
            throw new ExternalIntegrationException(erroMessage);
        }
    }

}

