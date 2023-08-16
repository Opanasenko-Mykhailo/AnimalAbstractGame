package org.example.entity.organism.animal;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.example.entity.organism.Movable;
import org.example.entity.organism.Organism;

@NoArgsConstructor
@SuperBuilder
@Getter
@EqualsAndHashCode
@ToString
public abstract class Animal implements Organism, Movable {
    private int maxWeight;
    private int maxAmount;
    private int maxSpeed;
    private int maxFood;
    private int maxAge;

    private boolean isAlive = true;
    private int weight;
    private int health;
    private int age;

    @Override
    public void play() {
        System.out.println("Animal start");
        this.eat();
        this.reproduce();
        this.move();
    }
    public void eat(){
        System.out.println("Animal eat");
        this.findFood();
    }
    public void findFood(){}
    @Override
    public void move() {
        System.out.println("Animal Move");
    }


}
