package me.lucaspmntl.geofreight.dto.melhorenvio.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MelhorEnvioResponseDTO(
        @JsonProperty("name")
        String transportName,

        @JsonProperty("price")
        BigDecimal transportCompanyPrice,

        @JsonProperty("delivery_time")
        Integer deliveryTime,
        CompanyDTO company
) {
}