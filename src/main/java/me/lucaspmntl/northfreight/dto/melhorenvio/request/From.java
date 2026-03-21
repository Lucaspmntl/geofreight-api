package me.lucaspmntl.geofreight.dto.melhorenvio.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record From(

        @JsonProperty("postal_code")
        String postalCode
) {
}
