package org.example.view;

import org.example.config.Statistics;
import org.example.engine.Simulation;
import org.example.entity.map.Cell;
import org.example.entity.map.GameMap;
import org.example.entity.organism.plant.Plant;
import org.example.factory.GameObjectPrototypeFactory;
import org.example.gameObjects.GameObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConsoleOutput {
    public static void printGameMapWithStatistics(GameMap gameMap) {
        Statistics statistics = Statistics.getInstance();
        int currentYear = statistics.getYear();
        int totalObjects = Statistics.calculateTotalObjects(gameMap);

        System.out.println("Year: " + currentYear);
        System.out.println("The total number of inhabitants of the island: " + totalObjects);
        if (totalObjects <= 0) {
            Simulation.getInstance().setIsEmptyIsland(true);
            System.out.println("The island is empty - game over  \uD83D\uDE2D");
        }

        Map<Class<? extends GameObject>, Integer> totalStatsMap = getTotalStatsMap(gameMap);

        for (Class<? extends GameObject> objectType : totalStatsMap.keySet()) {
            int totalCount = totalStatsMap.getOrDefault(objectType, 0);

            System.out.print(getIconForGameObjectType(objectType) +
                    ", |Total Count: " + totalCount + "|");

            if (!isPlant(objectType)) {
                int eatenCount = statistics.getGameObjectEatenMap().getOrDefault(objectType, 0);
                int diedFromStarvationCount = statistics.getDiedFromStarvationMap().getOrDefault(objectType, 0);
                int reproducedCount = statistics.getGameObjectsReproduceMap().getOrDefault(objectType, 0);

                System.out.print(", |Reproduced in last year: " + reproducedCount + "|" +
                        ", |Were eaten last year: " + eatenCount + "|" +
                        ", |Died of hunger last year: " + diedFromStarvationCount + "|");
            } else {
                int eatenCount = statistics.getGameObjectEatenMap().getOrDefault(objectType, 0);
                System.out.print(", |Were eaten last year: " + eatenCount + "|");
            }
            System.out.println();
        }
    }

    private static Map<Class<? extends GameObject>, Integer> getTotalStatsMap(GameMap gameMap) {
        Map<Class<? extends GameObject>, Integer> totalStatsMap = new HashMap<>();

        Cell[][] cells = gameMap.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                Map<Class<? extends GameObject>, Set<GameObject>> residentsMap = cells[row][column].getResidents();
                for (Set<GameObject> residentSet : residentsMap.values()) {
                    for (GameObject resident : residentSet) {
                        Class<? extends GameObject> objectType = resident.getClass();
                        totalStatsMap.putIfAbsent(objectType, 0);
                        totalStatsMap.put(objectType, totalStatsMap.get(objectType) + 1);
                    }
                }
            }
        }
        return totalStatsMap;
    }
    private static String getIconForGameObjectType(Class<? extends GameObject> objectType) {
        GameObject prototype = GameObjectPrototypeFactory.getInstance().getPrototypes().stream()
                .filter(obj -> obj.getClass() == objectType)
                .findFirst()
                .orElse(null);

        if (prototype != null) {
            return prototype.getGameObjectIcon();
        } else {
            return "Unknown Icon";
        }
    }
    private static boolean isPlant(Class<? extends GameObject> objectType) {
        return Plant.class.isAssignableFrom(objectType);
    }
}