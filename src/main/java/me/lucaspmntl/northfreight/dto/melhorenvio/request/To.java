package me.lucaspmntl.northfreight.dto.melhorenvio.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record To(
        @JsonProperty("postal_code")
        String postalCode
) {}
