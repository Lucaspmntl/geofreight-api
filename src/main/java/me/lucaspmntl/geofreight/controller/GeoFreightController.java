package me.lucaspmntl.geofreight.controller;

import me.lucaspmntl.geofreight.dto.GeoFreightRequestDTO;
import me.lucaspmntl.geofreight.dto.GeoFreightResponseDTO;
import me.lucaspmntl.geofreight.service.serviceimpl.FreightOrquestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/geofreight/api/v1")
public class GeoFreightController {

    @Autowired
    private FreightOrquestrator freightOrquestrator;

    @PostMapping(value = "freightsOptions/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<GeoFreightResponseDTO>> getFreightsOptions(
            @RequestBody GeoFreightRequestDTO geoFreightRequestDTO) {

            List<GeoFreightResponseDTO> response = freightOrquestrator.getFreightsOptions(geoFreightRequestDTO);

            return ResponseEntity.ok(response);
    }
}
