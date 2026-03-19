package me.lucaspmntl.geofreight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.Product;

import java.util.List;

public record GeoFreightRequestDTO(
        @JsonProperty("cep_origin")
        @NotBlank(message = "${required.field}")
        @Min(value = 8, message = "O CEP deve conter pelo menos 8 caracteres.")
        String cepOrigin,

        @JsonProperty("cep_destination")
        @NotBlank(message = "${required.field}")
        @Min(value = 8, message = "O CEP deve conter pelo menos 8 caracteres.")
        String cepDestination,

        @NotNull(message = "${required.field}")
        List<Product> products
) {
}
