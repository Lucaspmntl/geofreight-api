package me.lucaspmntl.geofreight.dto.melhorenvio.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record To(
        @JsonProperty("postal_code")
        @NotBlank(message = "${required.field}")
        @Min(value = 8, message = "O CEP deve conter pelo menos 8 caracteres.")
        String postalCode
) {}
