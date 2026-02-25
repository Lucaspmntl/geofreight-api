package me.lucaspmntl.geofreight.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BrasilApiDTO(
        LocationDTO location
) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record LocationDTO(
        String type,
        CoordinatesDTO coordinates
) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record CoordinatesDTO(
        Double longitude,
        Double latitude
) {}