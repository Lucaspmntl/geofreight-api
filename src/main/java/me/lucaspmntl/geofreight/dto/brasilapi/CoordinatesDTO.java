package me.lucaspmntl.geofreight.dto.brasilapi;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CoordinatesDTO(
        @JsonAlias("lon")
        Double longitude,

        @JsonAlias("lat")
        Double latitude
) {}
