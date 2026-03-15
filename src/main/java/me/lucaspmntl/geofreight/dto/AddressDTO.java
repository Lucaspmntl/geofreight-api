package me.lucaspmntl.geofreight.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressDTO(

        String cep,
        @JsonProperty("logradouro")
        String street,

        @JsonProperty("complemento")
        String complement,

        @JsonProperty("unidade")
        String unit,

        @JsonProperty("bairro")
        String neighborhood,

        @JsonProperty("localidade")
        String city,

        @JsonProperty("regiao")
        String region,

        String uf,

        @JsonProperty("estado")
        String state
) {
}