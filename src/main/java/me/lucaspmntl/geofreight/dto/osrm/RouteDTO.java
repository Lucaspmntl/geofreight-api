package me.lucaspmntl.geofreight.dto.osrm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RouteDTO(
        double duration,
        double distance
) {}
