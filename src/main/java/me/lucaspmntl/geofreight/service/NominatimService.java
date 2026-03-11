package me.lucaspmntl.geofreight.service;

import me.lucaspmntl.geofreight.dto.NominatimDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "nominatim", url = "${nominatim.url}")
public interface NominatimService {

    @GetMapping("/search?format=json&limit=2")
    List<NominatimDTO> getCoordinatesByAddress( @RequestParam("q") String query );
}
