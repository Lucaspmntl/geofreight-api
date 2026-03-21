package me.lucaspmntl.northfreight.service;

import me.lucaspmntl.northfreight.dto.melhorenvio.request.MelhorEnvioRequestDTO;
import me.lucaspmntl.northfreight.dto.melhorenvio.response.MelhorEnvioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "melhor-envio", url = "${melhor-envio.url}")
public interface MelhorEnvioService {

    @PostMapping(value = "/api/v2/me/shipment/calculate",
                consumes = "application/json",
                produces = "application/json")
    public List<MelhorEnvioResponseDTO> getFreights(
            @RequestHeader("Authorization") String token,
            @RequestHeader("User-Agent") String userAgent,
            @RequestBody MelhorEnvioRequestDTO melhorEnvioDTO
    );
}
