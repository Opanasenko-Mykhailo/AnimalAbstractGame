package org.example.entity.organism.animal;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.Probably;
import org.example.config.Statistics;
import org.example.entity.map.Cell;

import org.example.entity.behavior.CellHelper;
import org.example.entity.organism.Limits;
import org.example.entity.organism.Movable;
import org.example.entity.organism.Organism;
import org.example.factory.GameObjectPrototypeFactory;
import org.example.gameObjects.GameObject;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static org.example.entity.behavior.CellHelper.removeOrganism;

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
    private boolean isAlive = true;
    private boolean isReproduced = false;
    @Setter
    private boolean isEaten = false;
    private boolean isDiedFromStarvation = false;
    private int weight;
    @Setter
    private double famine;
    private int age;

    @Builder.Default
    private Map<String, Integer> stringTargetMatrix = new HashMap<>();
    @Builder.Default
    private Map<Class<? extends GameObject>, Integer> targetMatrix = new HashMap<>();

    @Override
    public void setGameObjectCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void play() {
        this.eat();
        this.move();

    }

    public void eat() {
        GameObject potencialFood = findPotentialFood();
        if (canEatFood(potencialFood)) {
            eatFood(potencialFood);
            Statistics.getInstance().incrementEatenAnimals();
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
                        .collect(Collectors.toList());

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
        double decreaseAmount = maxFood * 0.2; // 10% від maxFood
        setFamine(getFamine() - decreaseAmount);
        dieFromStarvation();
    }

    public void dieFromStarvation() {
        if (getFamine() < 0) {
            setAlive(false);
            Cell currentCell = getCell();
            if (currentCell != null) {
                synchronized (currentCell.getLock()) {
                    CellHelper.removeOrganism(currentCell, this);
                    Statistics.getInstance().incrementDiedFromStarvation();
                    isDiedFromStarvation = true;
                }
            }
        }
    }


    @Override
    public void move() {
        if (!isAlive || limits.getMaxSpeed() < 1) {
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

    public GameObject multiply() {
        if (Probably.calculate(10)) { // 10% ймовірність розмноження
            return GameObjectPrototypeFactory.getInstance().createPrototype(this.getClass());
        } else {
            return null;
        }
    }

    public void reproduceAnimal(Animal parentAnimal) {
        GameObject newAnimal = parentAnimal.multiply();
        if (newAnimal != null) {
            Cell currentCell = parentAnimal.getCell();
            if (currentCell != null) {
                synchronized (currentCell.getLock()) {
                    CellHelper.addOrganism(currentCell, newAnimal);
                    Statistics.getInstance().incrementBornAnimals();
                    isReproduced = true;
                }
            }
        }
    }
}