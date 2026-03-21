package me.lucaspmntl.northfreight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import me.lucaspmntl.northfreight.dto.melhorenvio.request.Product;

import java.util.List;

public record NorthFreightRequestDTO(
        @JsonProperty("cep_origin")
        @NotBlank(message = "${validation-error.required-field}")
        @Size(min = 8, message = "${validation-error.invalid-cep}")
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
