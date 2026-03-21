package me.lucaspmntl.geofreight.service.serviceimpl;

import me.lucaspmntl.geofreight.dto.AddressDTO;
import me.lucaspmntl.geofreight.dto.NorthFreightRequestDTO;
import me.lucaspmntl.geofreight.dto.NorthFreightResponseDTO;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class FreightOrquestrator {

    @Value("${melhor-envio.token}")
    private String token;

    @Value("${melhor-envio.email}")
    private String email;

    @Autowired
    ViaCepService viaCepService;

    @Autowired
    MelhorEnvioService melhorEnvioService;

    public List<NorthFreightResponseDTO> getFreightsOptions(NorthFreightRequestDTO dto) {

        MelhorEnvioRequestDTO melhorEnvioDto = new MelhorEnvioRequestDTO(
                new From(dto.cepOrigin()),
                new To(dto.cepDestination()),
                dto.products()
        );

        BigDecimal ferryCost = ferryPriceCalculator(dto.products());
        int ferryDays = 2;

        addressValidator(dto.cepOrigin(), dto.cepDestination());

        List<MelhorEnvioResponseDTO> response = melhorEnvioService
                .getFreights("Bearer "+ token, "GeoFreight-API (" + email + ")", melhorEnvioDto);

        return response.stream()
                .filter(obj -> obj.transportCompanyPrice() != null && obj.deliveryTime() != null)
                .map(obj -> new NorthFreightResponseDTO(
                        obj.transportName(),
                        obj.transportCompanyPrice(),
                        ferryCost,
                        obj.transportCompanyPrice().add(ferryCost),
                        obj.deliveryTime() + ferryDays,
                        obj.company()
                )).toList();
    }



    private BigDecimal ferryPriceCalculator(List<Product> products){
        double totalWeight = products.stream()
                .mapToDouble(Product::weight)
                .sum();

        double declaredValue = products.stream()
                .mapToDouble(Product::insuranceValue)
                .sum();

        double dispatchTariff = 35.00;
        double custPerKg = totalWeight * 2.50;
        double adValorem = declaredValue * 0.01;

        double result = (dispatchTariff + custPerKg + adValorem);

        return BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP);
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
            throw new NonAmapaAddresException("A rota informada é inválida: " +
                    "O serviço logístico exige que a origem ou o destino pertença ao estado do Amapá (AP).");

        if (amapaToAmapa) {
            throw new AmapaToAmapaException("A rota informada é inválida: " +
                    "Rotas de transporte logístico interno (origem e destino dentro do Amapá)" +
                    "não são suportadas por esta modalidade.");
        }
    }



    private AddressDTO getCepWithContext(String cep, String errorMessage){
        try{
            return viaCepService.getAddressByCep(cep);
        } catch (ExternalIntegrationException e) {
            throw new ExternalIntegrationException(errorMessage);
        }
    }

}

