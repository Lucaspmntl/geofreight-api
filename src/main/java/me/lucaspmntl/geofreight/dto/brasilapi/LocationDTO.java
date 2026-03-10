package me.lucaspmntl.geofreight.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.lucaspmntl.geofreight.dto.CoordinatesDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationDTO(
        String type,
        CoordinatesDTO coordinates
) {}
