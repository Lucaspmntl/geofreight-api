package me.lucaspmntl.geofreight.service;

import me.lucaspmntl.geofreight.dto.brasilapi.BrasilApiDTO;
import me.lucaspmntl.geofreight.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "brasil-api", url = "brasilapi.com.br/api/cep/v2/")
public interface BrasilApiService {

    @GetMapping("/{cep}")
    public BrasilApiDTO getAddressByCep(@PathVariable String cep);
}