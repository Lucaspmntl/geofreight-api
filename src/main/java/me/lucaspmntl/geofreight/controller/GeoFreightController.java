package me.lucaspmntl.geofreight.controller;

import me.lucaspmntl.geofreight.dto.GeoFreightResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/geofreight")
public class GeoFreightController {

    @PostMapping()
    public ResponseEntity<GeoFreightResponseDTO> getFreightsOptions() {
        return ResponseEntity.ok().build();
    }
}
