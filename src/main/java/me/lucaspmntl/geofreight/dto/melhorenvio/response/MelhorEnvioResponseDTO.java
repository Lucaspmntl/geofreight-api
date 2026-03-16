package me.lucaspmntl.geofreight.dto.melhorenvio.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MelhorEnvioResponseDTO(
        @JsonProperty("name")
        String transportName,

        @JsonProperty("price")
        Double transportCompanyPrice,

        @JsonProperty("delivery_time")
        Integer deliveryTime,
        CompanyDTO company
) {
}