package me.lucaspmntl.geofreight.service;

import me.lucaspmntl.geofreight.dto.melhorenvio.request.MelhorEnvioRequestDTO;
import me.lucaspmntl.geofreight.dto.melhorenvio.response.MelhorEnvioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "melhor-envio", url = "${melhor-envio.url}")
public interface MelhorEnvioService {

    @PostMapping("/api/v2/me/shipment/calculate")
    public List<MelhorEnvioResponseDTO> getFreights(@RequestBody MelhorEnvioRequestDTO melhorEnvioDTO);
}
