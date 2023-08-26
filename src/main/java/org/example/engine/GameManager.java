package org.example.engine;

import org.example.entity.map.Cell;
import org.example.entity.map.GameMap;
import org.example.entity.organism.animal.Animal;
import org.example.gameObjects.GameObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameManager {
    public void runGameManage() {
        Cell[][] cells = GameMap.getInstance().getCells();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                synchronized (cell.getLock()) {
                    Map<Class<? extends GameObject>, Set<GameObject>> residents = new HashMap<>(cell.getResidents());
                    for (Map.Entry<Class<? extends GameObject>, Set<GameObject>> entry : residents.entrySet()) {
                        Class<? extends GameObject> residentType = entry.getKey();
                        Set<GameObject> residentSet = new HashSet<>(entry.getValue());
                        for (GameObject resident : residentSet) {
                            if (Animal.class.isAssignableFrom(residentType)) {
                                Animal animal = (Animal) resident;
                                synchronized (animal.getLock()) {
                                    animal.play();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
