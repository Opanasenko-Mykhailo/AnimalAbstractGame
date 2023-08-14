package org.example.organism.animal;


import org.example.config.ConfigYamlPatch;
import org.example.gameObjects.GameObjectLabel;
import org.example.organism.Organism;

public abstract class Animal implements Organism, GameObjectLabel, ConfigYamlPatch {
    private int maxWeight;
    private int maxAmount;
    private int maxSpeed;
    private int maxFood;
    private int maxAge;

    private boolean isAlive = true;
    private int weight;
    private int health;
    private int age;


}
