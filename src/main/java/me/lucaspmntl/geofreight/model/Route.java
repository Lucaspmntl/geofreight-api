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
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Address originAddress;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Address destinationAddress;

    @ManyToOne
    private Modal modal;

}
