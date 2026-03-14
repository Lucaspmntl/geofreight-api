package me.lucaspmntl.geofreight.dto.melhorenvio.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Product(
        int width,
        int height,
        int length,
        double weight,

        @JsonProperty("insurance_value")
        double insuranceValue,

        int quantity
) {}
