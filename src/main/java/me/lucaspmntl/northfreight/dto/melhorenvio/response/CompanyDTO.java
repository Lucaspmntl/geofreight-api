package me.lucaspmntl.northfreight.dto.melhorenvio.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CompanyDTO(
        String name
) {
}