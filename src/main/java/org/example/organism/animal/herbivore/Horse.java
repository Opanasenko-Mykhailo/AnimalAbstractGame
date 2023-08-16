package org.example.organism.animal.herbivore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.example.gameObjects.GameObject;
import org.example.gameObjects.GameObjectLabel;
import org.example.organism.animal.predator.Wolf;

@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Horse extends Herbivore implements GameObjectLabel {

    @Override
    public void play() {
        System.out.println("Hello");

    }

    @Override
    public Horse reproduce() {
        return Horse.builder()
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
