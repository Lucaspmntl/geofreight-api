package me.lucaspmntl.northfreight.dto;

import me.lucaspmntl.northfreight.dto.melhorenvio.response.CompanyDTO;

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