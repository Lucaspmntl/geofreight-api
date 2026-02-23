package me.lucaspmntl.geofreight.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @Setter(AccessLevel.NONE)
    private String cep;
    private String street;
    private String complement;
    private String unit;
    private String neighborhood;
    private String city;
    private String uf;
    private String state;
    private String ibge;
    private String ddd;
    private String gia;
    private String siafi;
}
