package org.example;

import org.example.gameObjects.GameObject;

import java.util.Set;

public class GameWorker {

    private final Set<GameObject> gameObjects;

    public GameWorker(Set<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void play() {
        gameObjects.forEach(GameObject::play);
    }
}

