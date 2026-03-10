package me.lucaspmntl.geofreight.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.lucaspmntl.geofreight.dto.LocationDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BrasilApiDTO(
        LocationDTO location
) {}
