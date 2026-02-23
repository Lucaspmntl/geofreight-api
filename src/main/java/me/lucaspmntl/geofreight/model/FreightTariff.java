package me.lucaspmntl.geofreight.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class FreightTariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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
