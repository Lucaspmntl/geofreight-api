package me.lucaspmntl.geofreight.dto.melhorenvio.request;

import java.util.List;

public record MelhorEnvioRequestDTO(
        From from,
        To to,
        List<Product> products
) {
}
