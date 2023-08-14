package org.example.config;

public class GameObjectsScanner {
    private static GameObjectsScanner instance;

    private GameObjectsScanner() {
    }
    public static GameObjectsScanner getInstance() {
        if (instance == null) {
            instance = new GameObjectsScanner();
        }
        return instance;
    }
}
