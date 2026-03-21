package me.lucaspmntl.northfreight.controller;

import jakarta.validation.Valid;
import me.lucaspmntl.northfreight.dto.NorthFreightRequestDTO;
import me.lucaspmntl.northfreight.dto.NorthFreightResponseDTO;
import me.lucaspmntl.northfreight.service.serviceimpl.FreightOrquestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NorthFreightController {

    @Autowired
    private FreightOrquestrator freightOrquestrator;

    @PostMapping(value = "/shippings", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<NorthFreightResponseDTO>> getFreightsOptions(
            @RequestBody @Valid NorthFreightRequestDTO requestDto) {

            List<NorthFreightResponseDTO> response = freightOrquestrator.getFreightsOptions(requestDto);

            return ResponseEntity.ok(response);
    }
}
