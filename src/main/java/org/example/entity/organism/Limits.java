package org.example.entity.organism;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@EqualsAndHashCode
@ToString
public class Limits {
    private int maxWeight;
    private int maxAmount;
    private int maxSpeed;
    private int maxFood;
}
