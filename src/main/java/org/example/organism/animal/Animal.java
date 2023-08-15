package org.example.organism.animal;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.example.gameObjects.GameObjectLabel;
import org.example.organism.Organism;
@NoArgsConstructor
@SuperBuilder
@Getter
@EqualsAndHashCode
@ToString
public abstract class Animal implements Organism{
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
