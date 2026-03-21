package me.lucaspmntl.geofreight.controller;

import jakarta.validation.Valid;
import me.lucaspmntl.geofreight.dto.NorthFreightRequestDTO;
import me.lucaspmntl.geofreight.dto.NorthFreightResponseDTO;
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
    public ResponseEntity<List<NorthFreightResponseDTO>> getFreightsOptions(
            @RequestBody @Valid NorthFreightRequestDTO requestDto) {

            List<NorthFreightResponseDTO> response = freightOrquestrator.getFreightsOptions(requestDto);

            return ResponseEntity.ok(response);
    }
}
