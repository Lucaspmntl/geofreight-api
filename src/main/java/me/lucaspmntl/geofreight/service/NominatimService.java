package me.lucaspmntl.geofreight.service;

import me.lucaspmntl.geofreight.dto.NominatimDTO;
import me.lucaspmntl.geofreight.dto.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "nominatim", url = "https://nominatim.openstreetmap.org")
public interface NominatimService {

    @GetMapping("/search")
    List<NominatimDTO> getCoordinatesByAddress(
            @RequestParam("q") String query,
            @RequestParam("format") String format,
            @RequestParam("limit") int limit
    );
}
