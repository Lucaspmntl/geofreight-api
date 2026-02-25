package me.lucaspmntl.geofreight.service;

import me.lucaspmntl.geofreight.dto.OsrmDistanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "osrm", url = "http://router.project-osrm.org/route/v1/driving")
public interface OsrmService {

    @GetMapping("/{longitudeOrigin}," +
            "{latitudeOrigin};" +
            "{longitudeDestination}," +
            "{latitudeDestination}" +
            "?overview=false")
    public OsrmDistanceDTO getDistance(@PathVariable String longitudeOrigin,
                                       @PathVariable String latitudeOrigin,
                                       @PathVariable String longitudeDestination,
                                       @PathVariable String latitudeDestination);
}
