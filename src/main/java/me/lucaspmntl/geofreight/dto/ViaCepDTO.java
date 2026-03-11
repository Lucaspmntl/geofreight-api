package me.lucaspmntl.geofreight.dto;

public record ViaCepDTO(
        String cep,
        String street,
        String complement,
        String unit,
        String neighborhood,
        String city,
        String uf,
        String state,
        String ibge,
        String ddd,
        String gia,
        String siafi,

        Double latitude,
        Double longitude
) {
}