package me.lucaspmntl.geofreight.model;

import jakarta.persistence.*;

@Entity
public class FreightCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Address originAddress;
    private Address destinationAddress;
    private Modal modal;

    @Column(name = "calculatedDistanceKm")
    private Float calculatedDistance;
    
}
