package me.lucaspmntl.geofreight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.Product;

import java.util.List;

public record GeoFreightRequestDTO(
        @JsonProperty("cep_origin")
        @NotBlank(message = "${validation-error.required-field}")
        @Min(value = 8, message = "${validation-error.invalid-cep}")
        String cepOrigin,

        @JsonProperty("cep_destination")
        @NotBlank(message = "${validation-error.invalid-cep}")
        @Size(min = 8, message = "${validation-error.invalid-cep}")
        String cepDestination,

        @NotNull(message = "${required.field}")
        @Valid
        List<Product> products
) {
}
