package me.lucaspmntl.geofreight.dto.osrm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OsrmDistanceDTO(
    List<RouteDTO> routes
) {}
