package me.lucaspmntl.geofreight.dto.melhorenvio.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record From(
        @JsonProperty("postal_code")
        String postalCode
) {
}
