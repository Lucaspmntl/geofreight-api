package me.lucaspmntl.geofreight.model;

import jakarta.persistence.*;

@Entity
public class FreightTariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Modal modal;

    private Float minWeight;
    private Float maxWeight;

    private Float maxDistanceKm;
    private Float minDistanceKm;

    private Float basePrice;
    private Float pricePerKm;

}
