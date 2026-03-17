package me.lucaspmntl.geofreight.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import me.lucaspmntl.geofreight.dto.melhorenvio.response.CompanyDTO;

public record GeoFreightResponseDTO(

        String transportName,

        Double transportCompanyPrice,
        double ferryPrice,

        double totalPrice,

        Integer deliveryTime,
        CompanyDTO company
) {
}