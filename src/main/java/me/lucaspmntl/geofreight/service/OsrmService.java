package me.lucaspmntl.geofreight.service;

import me.lucaspmntl.geofreight.dto.osrm.OsrmDistanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "osrm", url = "${osrm.url}")
/*
  Cliente Feign para integração com o serviço OSRM.
  Responsável por calcular a distância entre dois pontos.
 */
public interface OsrmService {

    /**
     * @param longitudeOrigin -> longitude de origem
     * @param latitudeOrigin -> latitude de origem
     * @param longitudeDestination -> longitude de destino
     * @param latitudeDestination -> latitude de destino
     *
     * @return {@link OsrmDistanceDTO} -> A distância respectiva e sua duração
     */
    @GetMapping("/route/v1/driving" +
            "/{longitudeOrigin}," +
            "{latitudeOrigin};" +
            "{longitudeDestination}," +
            "{latitudeDestination}" +
            "?overview=false")
    public OsrmDistanceDTO getDistance(@PathVariable double longitudeOrigin,
                                       @PathVariable double latitudeOrigin,
                                       @PathVariable double longitudeDestination,
                                       @PathVariable double latitudeDestination);
}

