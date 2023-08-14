package org.example.organism.plant;

import org.example.config.ConfigYamlPatch;
import org.example.gameObjects.GameObjectLabel;
import org.example.organism.Organism;

public abstract class Plant implements Organism, GameObjectLabel, ConfigYamlPatch {
    private int maxWeight;
    private int maxAmount;
}
