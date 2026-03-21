package me.lucaspmntl.northfreight.dto.melhorenvio.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Product(

        @NotNull int width,
        @NotNull int height,
        @NotNull int length,
        @NotNull double weight,

        @JsonProperty("insurance_value")
        @NotNull double insuranceValue,

        @NotNull
        @Positive(message = "${validation-message.min-number}")
        int quantity
) {}
