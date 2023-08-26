package org.example.entity.organism.animal;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.utils.Probably;
import org.example.config.Statistics;
import org.example.entity.map.Cell;

import org.example.entity.map.CellHelper;
import org.example.entity.organism.Limits;
import org.example.entity.organism.Movable;
import org.example.entity.organism.Organism;
import org.example.factory.GameObjectPrototypeFactory;
import org.example.gameObjects.GameObject;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import static org.example.entity.map.CellHelper.removeOrganism;

@NoArgsConstructor
@SuperBuilder
@Getter
@ToString
public abstract class Animal implements Organism, Movable {
    private static long serialUID = 1L;
    @Builder.Default
    private final long UID = serialUID++;
    @Builder.Default
    private ReentrantLock lock = new ReentrantLock();
    @JsonIgnore
    private Cell cell;
    private String icon;
    private Limits limits;
    @Setter
    private double famine;

    private final int PROBABILITY_OF_REPRODUCTION = 10;
    private final int DECREASED_HUNGER = 20;

    @Builder.Default
    private Map<String, Integer> stringTargetMatrix = new HashMap<>();
    @Builder.Default
    private Map<Class<? extends GameObject>, Integer> targetMatrix = new HashMap<>();

    @Override
    public void setGameObjectCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String getGameObjectIcon(){
        return icon;
    }

    @Override
    public void play() {
        this.move();
        this.eat();
        this.reproduceAnimal(this);
        this.decreaseFamine();
    }

    private void eat() {
        GameObject potentialFood = findPotentialFood();
        if (canEatFood(potentialFood)) {
            assert potentialFood != null;
            eatFood(potentialFood);
            Statistics.getInstance().gameObjectEaten(potentialFood);
        }
    }

    private void eatFood(GameObject food) {
        Cell currentCell = getCell();
        if (currentCell != null) {
            synchronized (currentCell.getLock()) {
                double currentFamine = getFamine();
                double maxFood = limits.getMaxFood();
                double foodWeight = food.getLimits().getMaxWeight();

                double newFamine = currentFamine + foodWeight;
                if (newFamine > maxFood) {
                    newFamine = maxFood;
                }

                setFamine(newFamine);
                removeOrganism(currentCell, food);
            }
        }
    }

    private int calculateFoodProbability(GameObject foodObject) {
        if (foodObject instanceof Organism) {
            String foodName = foodObject.getClass().getSimpleName();
            Integer probability = stringTargetMatrix.get(foodName);
            return probability != null ? probability : 0;
        }
        return 0;
    }

    private Organism findPotentialFood() {
        Cell currentCell = getCell();
        if (currentCell != null) {
            synchronized (currentCell.getLock()) {
                Map<Class<? extends GameObject>, Set<GameObject>> residentsMap = currentCell.getResidents();

                List<Organism> potentialFood = residentsMap.entrySet().stream()
                        .filter(entry -> entry.getKey() != this.getClass()) // Відмінний від поточного виду
                        .flatMap(entry -> entry.getValue().stream())
                        .filter(resident -> resident instanceof Organism)
                        .map(resident -> (Organism) resident)
                        .toList();

                if (!potentialFood.isEmpty()) {
                    int randomIndex = Probably.randomInt(0, potentialFood.size());
                    return potentialFood.get(randomIndex);
                }
            }
        }
        return null;
    }

    private boolean canEatFood(GameObject food) {
        int foodProbability = calculateFoodProbability(food);
        return Probably.calculate(foodProbability);
    }

    public void decreaseFamine() {
        double maxFood = limits.getMaxFood();
        double decreaseAmount = maxFood * DECREASED_HUNGER / 100; // % від maxFood
        setFamine(getFamine() - decreaseAmount);
        dieFromStarvation();
    }

    public void dieFromStarvation() {
        if (getFamine() < 0) {
            Cell currentCell = getCell();
            if (currentCell != null) {
                synchronized (currentCell.getLock()) {
                    CellHelper.removeOrganism(currentCell, this);
                    Statistics.getInstance().diedFromStarvation(this);
                }
            }
        }
    }

    @Override
    public void move() {
        if (limits.getMaxSpeed() < 1) {
            return;
        }
        Cell currentCell = getCell();
        if (currentCell == null) {
            return;
        }
        synchronized (currentCell.getLock()) {
            List<Cell> neighborCells = new ArrayList<>(CellHelper.getNeighborCells(currentCell));
            if (!neighborCells.isEmpty()) {
                int distance = Probably.randomInt(1, limits.getMaxSpeed() + 1);
                Cell nextCell = CellHelper.chooseNextCell(neighborCells, distance);
                if (nextCell != null) {
                    synchronized (nextCell.getLock()) {
                        CellHelper.moveOrganismToCell(currentCell, nextCell, this);
                    }
                }
            }
        }
    }

    public void reproduceAnimal(Animal parentAnimal) {
        GameObject newAnimal = parentAnimal.multiply();
        if (newAnimal != null) {
            Cell currentCell = parentAnimal.getCell();
            if (currentCell != null) {
                synchronized (currentCell.getLock()) {
                    CellHelper.addOrganism(currentCell, newAnimal);
                    Statistics.getInstance().gameObjectsReproduce(this);
                }
            }
        }
    }

    public GameObject multiply() {
        if (Probably.calculate(PROBABILITY_OF_REPRODUCTION)) { //Ймовірність розмноження
            return GameObjectPrototypeFactory.getInstance().createPrototype(this.getClass());
        } else {
            return null;
        }
    }
}