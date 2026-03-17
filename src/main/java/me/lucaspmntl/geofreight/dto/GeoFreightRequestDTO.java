package me.lucaspmntl.geofreight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.Product;

import java.util.List;

public record GeoFreightRequestDTO(
        @JsonProperty("cep_origin")
        String cepOrigin,

        @JsonProperty("cep_destination")
        String cepDestination,
        List<Product> products
) {
}
