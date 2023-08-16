package org.example.organism.animal.predator;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.example.gameObjects.GameObjectLabel;

@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Wolf extends Predator implements GameObjectLabel {

    @Override
    public void play() {
        System.out.println("Wolf hello");
    }

    @Override
    public Wolf reproduce() {
        return Wolf.builder()
                .maxFood(this.getMaxFood())
                .maxSpeed(this.getMaxSpeed())
                .maxWeight(1)
                .maxAmount(1)
                .maxAge(1)
                .isAlive(true)
                .weight(1)
                .health(1)
                .age(1)
                .build();
    }
}
