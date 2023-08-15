package org.example.organism.animal.herbivore;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.example.gameObjects.GameObject;
import org.example.gameObjects.GameObjectLabel;

@SuperBuilder
@ToString
public class Horse extends Herbivore implements GameObjectLabel {

    @Override
    public void play() {
        System.out.println("Hello");

    }

    @Override
    public GameObject reproduce() {
        return null;
    }

}
