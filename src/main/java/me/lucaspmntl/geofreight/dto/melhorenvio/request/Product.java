package me.lucaspmntl.geofreight.dto.melhorenvio.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Product(
        String id,
        int width,
        int height,
        int length,
        double weight,

        @JsonProperty("insurance_value")
        double insuranceValue,

        int quantity
) {}
