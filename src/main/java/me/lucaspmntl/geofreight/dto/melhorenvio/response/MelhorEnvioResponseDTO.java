package me.lucaspmntl.geofreight.dto.melhorenvio.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MelhorEnvioResponseDTO(
        String name,
        Double price,

        @JsonProperty("delivery_time")
        Integer deliveryTime,
        CompanyDTO company
) {
}