package org.example;

import org.example.config.Statistics;
import org.example.entity.map.Cell;
import org.example.entity.map.GameMap;
import org.example.gameObjects.GameObject;

import java.util.Map;
import java.util.Set;

public class ConsoleOutput {
    public static void printGameMapWithStatistics(GameMap gameMap) {
        Statistics statistics = Statistics.getInstance();
        int currentYear = statistics.getYear();
        int bornAnimals = statistics.getBornAnimals();
        int eatenAnimals = statistics.getEatenAnimals();
        int diedFromStarvation = statistics.getDiedFromStarvation();

        System.out.println("Year: " + currentYear);
        System.out.println("Born Animals: " + bornAnimals);
        System.out.println("Eaten Animals: " + eatenAnimals);
        System.out.println("Died from Starvation: " + diedFromStarvation);

        Cell[][] cells = gameMap.getCells();

        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                Cell cell = cells[row][column];
                Map<Class<? extends GameObject>, Set<GameObject>> residentsMap = cell.getResidents();

                System.out.print("Cell[" + row + "][" + column + "]: ");
                if (residentsMap.isEmpty()) {
                    System.out.println("No residents");
                } else {
                    for (Map.Entry<Class<? extends GameObject>, Set<GameObject>> entry : residentsMap.entrySet()) {
                        Class<? extends GameObject> residentType = entry.getKey();
                        Set<GameObject> residentSet = entry.getValue();
                        if (!residentSet.isEmpty()) {
                            String icon = residentSet.iterator().next().getIcon();
                            System.out.print(icon + " (" + residentSet.size() + ") ");
                        } else {
                            System.out.print("No residents ");
                        }
                    }
                    System.out.println();
                }
            }
        }
    }
}