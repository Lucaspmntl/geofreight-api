package me.lucaspmntl.geofreight.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import me.lucaspmntl.geofreight.model.Address;
import me.lucaspmntl.geofreight.model.Coordinates;

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
        String state,

        String ibge,
        String ddd,
        String gia,
        String siafi,

        @Embedded
        Coordinates coordinates
) {
       public static AddressDTO fromEntity(Address address){
               if (address == null) return null;

               return new AddressDTO(
                       address.getCep(),
                       address.getStreet(),
                       address.getComplement(),
                       address.getUnit(),
                       address.getNeighborhood(),
                       address.getCity(),
                       address.getRegion(),
                       address.getUf(),
                       address.getState(),
                       address.getIbge(),
                       address.getDdd(),
                       address.getGia(),
                       address.getSiafi(),
                       address.getCoordinates()
               );
       }
}