package org.example.config;

import org.example.gameObjects.GameObjectLabel;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;

public class GameObjectsScanner {
    private static GameObjectsScanner instance;
    private final Reflections reflections = new Reflections("org.example");

    private GameObjectsScanner() {
    }

    public static GameObjectsScanner getInstance() {
        if (instance == null) {
            instance = new GameObjectsScanner();
        }
        return instance;
    }

    public Set<Class<? extends GameObjectLabel>> getAllGameObjectsClasses() {
        Set<Class<? extends GameObjectLabel>> classes = reflections.getSubTypesOf(GameObjectLabel.class);
        return classes;
    }
}