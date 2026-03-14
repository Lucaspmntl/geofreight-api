package me.lucaspmntl.geofreight.dto.melhorenvio.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record To(
        @JsonProperty("postal_code")
        String postalCode
) {}
