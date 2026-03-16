package me.lucaspmntl.geofreight.dto;

import me.lucaspmntl.geofreight.dto.melhorenvio.request.Product;

import java.util.List;

public record GeoFreightRequestDTO(
        String cepOrigin,
        String cepDestination,
        List<Product> products
) {
}
