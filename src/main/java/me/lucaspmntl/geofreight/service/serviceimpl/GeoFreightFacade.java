package me.lucaspmntl.geofreight.service.serviceimpl;

import me.lucaspmntl.geofreight.dto.AddressDTO;
import me.lucaspmntl.geofreight.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacadeServiceImpl {

    // 68902-861 - macapa
    // 01310-200 - sao paulo

    @Autowired
    FreightOrquestrator freightOrquestrator;

    public void getFreight(String originCep, String destinationCep){

        AddressDTO origin = freightOrquestrator.getAddress(originCep);
        AddressDTO destination = freightOrquestrator.getAddress(destinationCep);

    }
}
