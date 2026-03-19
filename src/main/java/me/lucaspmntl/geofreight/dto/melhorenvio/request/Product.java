package me.lucaspmntl.geofreight.dto.melhorenvio.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Product(

        @NotEmpty int width,
        @NotEmpty int height,
        @NotEmpty int length,
        @NotEmpty double weight,

        @JsonProperty("insurance_value")
        @NotEmpty double insuranceValue,

        @NotEmpty
        @Min(value = 1, message = "${validation-message.min-number}")
        int quantity
) {}
