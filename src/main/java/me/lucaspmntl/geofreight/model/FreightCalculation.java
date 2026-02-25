package me.lucaspmntl.geofreight.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class FreightCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Address originAddress;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Address destinationAddress;

    @ManyToOne
    private Modal modal;

    @ManyToOne
    private Carrier carrier;

    private Double calculatedDistanceKm;
    private Double loadWeightKg;
    private BigDecimal finalPrice;
    private Integer estimatedTimeDays;
}
