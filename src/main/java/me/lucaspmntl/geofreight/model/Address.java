package me.lucaspmntl.geofreight.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {

    @Id
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
