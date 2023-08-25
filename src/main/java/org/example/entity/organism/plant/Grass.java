package org.example.entity.organism.plant;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import org.example.gameObjects.GameObjectLabel;

@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Grass extends Plant implements GameObjectLabel {


    @Override
    public Grass reproduce() {
        return Grass.builder()
                .limits(getLimits())
                .icon(getIcon())
                .build();
    }
}
