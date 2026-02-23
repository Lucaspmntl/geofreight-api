package me.lucaspmntl.geofreight.service;

import me.lucaspmntl.geofreight.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "via-cep", url = "viacep.com.br/ws")
public interface ViaCepService {

    @GetMapping("/{cep}/json")
    public Address getAddressByCep(@PathVariable String cep);
}