package me.lucaspmntl.northfreight.dto;

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

        String uf,

        @JsonProperty("estado")
        String state,

        @JsonProperty("regiao")
        String region
) {
}