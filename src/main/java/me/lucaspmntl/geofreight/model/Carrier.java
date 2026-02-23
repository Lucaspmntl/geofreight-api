package me.lucaspmntl.geofreight.model;

import jakarta.persistence.*;

@Entity
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private FreightTariff freightTariff;

    private String name;
    private String cnpj;
    private String email;


}
