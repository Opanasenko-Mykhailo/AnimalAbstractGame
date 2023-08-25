package org.example.entity.organism.animal.predator;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.example.gameObjects.GameObjectLabel;

@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Boa extends Predator implements GameObjectLabel {

    @Override
    public Boa reproduce() {
        return Boa.builder()
                .limits(getLimits())
                .isAlive(true)
                .icon(getIcon())
                .stringTargetMatrix(getStringTargetMatrix())
                .famine(getLimits().getMaxFood())
                .age(1)
                .build();
    }
}
