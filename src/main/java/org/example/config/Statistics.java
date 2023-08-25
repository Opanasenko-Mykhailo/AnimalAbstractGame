package org.example.config;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Statistics {
    @Setter
    private int year;
    private int bornAnimals;
    private int eatenAnimals;
    private int diedFromStarvation;

    private static Statistics instance;

    public static Statistics getInstance() {
        if (instance == null) {
            instance = new Statistics();
        }
        return instance;
    }

    private Statistics() {
    }

    public void incrementBornAnimals() {
        bornAnimals++;
    }

    public void incrementEatenAnimals() {
        eatenAnimals++;
    }

    public void incrementDiedFromStarvation() {
        diedFromStarvation++;
    }
    public void resetCounters() {
        bornAnimals = 0;
        eatenAnimals = 0;
        diedFromStarvation = 0;
    }
}