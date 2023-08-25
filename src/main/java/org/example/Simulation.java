package org.example;

import org.example.config.Statistics;
import org.example.entity.map.GameMap;

public class Simulation {
    private static Simulation instance;

    public static Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    private Simulation() {
    }
    public void startSimulation() {
        while (true) {
            Statistics.getInstance().resetCounters();

            int year = Statistics.getInstance().getYear();
            year++;
            Statistics.getInstance().setYear(year);
            AnimalManager animalManager = new AnimalManager();
            animalManager.moveAndManageAnimals();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("__________N__E__X__T__________");
            ConsoleOutput.printGameMapWithStatistics(GameMap.getInstance());
        }
    }
}
