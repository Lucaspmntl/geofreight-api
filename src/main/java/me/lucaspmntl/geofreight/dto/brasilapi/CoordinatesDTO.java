package me.lucaspmntl.geofreight.dto.brasilapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CoordinatesDTO(Double longitude, Double latitude) {
}
