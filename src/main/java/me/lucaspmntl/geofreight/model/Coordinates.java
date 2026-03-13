package me.lucaspmntl.geofreight.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    Double longitude;
    Double latitude;
}
