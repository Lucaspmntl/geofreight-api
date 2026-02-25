package me.lucaspmntl.geofreight.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OsrmDistanceDTO(
    List<RouteDTO> routes
) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record RouteDTO(
        double duration,
        double distance
) {}

