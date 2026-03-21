package me.lucaspmntl.geofreight.dto;

import me.lucaspmntl.geofreight.dto.melhorenvio.response.CompanyDTO;

import java.math.BigDecimal;

public record NorthFreightResponseDTO(

        String transportName,

        BigDecimal transportCompanyPrice,
        BigDecimal ferryPrice,

        BigDecimal totalPrice,

        Integer deliveryTime,
        CompanyDTO company
) {
}