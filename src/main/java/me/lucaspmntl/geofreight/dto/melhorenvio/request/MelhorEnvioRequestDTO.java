package me.lucaspmntl.geofreight.dto.melhorenvio.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MelhorEnvioRequestDTO(

        @NotNull(message = "${required.field}")
        From from,

        @NotNull(message = "${required.field}")
        To to,

        @NotNull(message = "${required.field}")
        List<Product> products
) {
}
