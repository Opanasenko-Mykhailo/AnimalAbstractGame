package org.example.entity.organism.animal.herbivore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.example.gameObjects.GameObjectLabel;

@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Boar extends Herbivore implements GameObjectLabel {
    @Override
    public Boar reproduce() {
        return Boar.builder()
                .limits(getLimits())
                .isAlive(true)
                .icon(getIcon())
                .stringTargetMatrix(getStringTargetMatrix())
                .famine(getLimits().getMaxFood())
                .age(1)
                .build();
    }

}
