package me.lucaspmntl.geofreight.controller;

import me.lucaspmntl.geofreight.dto.GeoFreightRequestDTO;
import me.lucaspmntl.geofreight.dto.GeoFreightResponseDTO;
import me.lucaspmntl.geofreight.service.serviceimpl.FreightOrquestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GeoFreightController {

    @Autowired
    private FreightOrquestrator freightOrquestrator;

    @PostMapping(value = "/shippings", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<GeoFreightResponseDTO>> getFreightsOptions(
            @RequestBody GeoFreightRequestDTO geoFreightRequestDTO) {

            List<GeoFreightResponseDTO> response = freightOrquestrator.getFreightsOptions(geoFreightRequestDTO);

            return ResponseEntity.ok(response);
    }
}
