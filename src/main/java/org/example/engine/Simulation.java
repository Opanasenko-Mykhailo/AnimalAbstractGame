package org.example.engine;

import lombok.Setter;
import org.example.config.Statistics;
import org.example.entity.map.GameMap;
import org.example.view.ConsoleOutput;

import java.util.concurrent.*;

public class Simulation {
    private static Simulation instance;
    @Setter
    private Boolean isEmptyIsland = false;
    private final ExecutorService simulationExecutor;
    private final ExecutorService outputExecutor;
    private final Object lock = new Object();
    private boolean shouldPrintStats = true;

    public static Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    private Simulation() {
        simulationExecutor = Executors.newSingleThreadExecutor();
        outputExecutor = Executors.newSingleThreadExecutor();
    }

    public void startSimulation() {
        ConsoleOutput.printGameMapWithStatistics(GameMap.getInstance());

        outputExecutor.execute(() -> {
            while (!isEmptyIsland) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    if (shouldPrintStats) {
                        System.out.println("__________N__E__X__T__________");
                        ConsoleOutput.printGameMapWithStatistics(GameMap.getInstance());
                        shouldPrintStats = false;
                        lock.notify();
                    }
                }
            }
        });

        simulationExecutor.execute(() -> {
            while (!isEmptyIsland) {
                Statistics.getInstance().resetCounters();

                int year = Statistics.getInstance().getYear();
                year++;
                Statistics.getInstance().setYear(year);
                GameManager gameManager = new GameManager();
                gameManager.runGameManage();

                synchronized (lock) {
                    shouldPrintStats = true;
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            // Завершити виконання програми
            simulationExecutor.shutdown();
            outputExecutor.shutdown();
        });
    }
}
