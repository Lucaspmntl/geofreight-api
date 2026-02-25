package me.lucaspmntl.geofreight.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToMany
    @JoinColumn(name = "carrier_id")
    private List<FreightTariff> freightTariff;

    private String name;
    private String cnpj;
    private String email;


}
