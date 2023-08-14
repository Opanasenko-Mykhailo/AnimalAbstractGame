package org.example.organism.animal.predator;

import org.example.config.ConfigYamlPathEnum;
import org.example.gameObjects.GameObject;

public class Wolf extends Predator{
    @Override
    public String getYamlPatch() {
        return ConfigYamlPathEnum.WOLF.getYamlPath();
    }

    @Override
    public void play() {

    }

    @Override
    public GameObject reproduce() {
        return null;
    }
}
