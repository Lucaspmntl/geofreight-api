package me.lucaspmntl.geofreight.dto.brasilapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.lucaspmntl.geofreight.dto.brasilapi.CoordinatesDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationDTO(
        String type,
        CoordinatesDTO coordinates
) {}
