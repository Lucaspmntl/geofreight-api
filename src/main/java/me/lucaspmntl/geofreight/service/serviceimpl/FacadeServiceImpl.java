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
    ViaCepService viaCepService;

    public void getFreight(String originCep, String destinationCep){

        AddressDTO origin = viaCepService.getAddressByCep(originCep);
        AddressDTO destination = viaCepService.getAddressByCep(destinationCep);

    }
}
