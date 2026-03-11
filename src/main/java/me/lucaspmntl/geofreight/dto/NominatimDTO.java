package me.lucaspmntl.geofreight.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NominatimDTO(
        String lat,
        String lon,
        String type
) {
}
