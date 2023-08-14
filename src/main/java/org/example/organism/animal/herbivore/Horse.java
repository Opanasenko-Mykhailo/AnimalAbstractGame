package org.example.organism.animal.herbivore;

import org.example.config.ConfigYamlPathEnum;
import org.example.gameObjects.GameObject;

public class Horse extends Herbivore{

    @Override
    public void play() {

    }

    @Override
    public GameObject reproduce() {
        return null;
    }

    @Override
    public String getYamlPatch() {
        return ConfigYamlPathEnum.HORSE.getYamlPath();
    }
}
