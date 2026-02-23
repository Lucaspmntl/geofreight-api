package me.lucaspmntl.geofreight.dto.brasilapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BrasilApiDTO(LocationDTO location) {
}
