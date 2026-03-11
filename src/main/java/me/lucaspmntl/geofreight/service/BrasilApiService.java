package me.lucaspmntl.geofreight.service;

import me.lucaspmntl.geofreight.dto.brasilapi.BrasilApiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "brasil-api", url = "${brasilapi.url}")
/**
 * Cliente Feign para integração com a BrasilAPI.
 * Objetivo de obter dados de latitude e longitude para uso em {@link OsrmService}.
 */
public interface BrasilApiService {

    /**
     * @param cep -> CEP informado
     * @return {@link BrasilApiDTO} -> Contém os dados de longitude e latitude
     */
    @GetMapping("/{cep}")
    public BrasilApiDTO getCoordinatesByCep(@PathVariable String cep);
}