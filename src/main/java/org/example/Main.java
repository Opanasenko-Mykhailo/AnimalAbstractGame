package org.example;

import org.example.config.AppConfigurator;
import org.example.factory.GameObjectPrototypeFactory;
import org.example.gameObjects.GameObject;
import org.example.organism.animal.herbivore.Horse;
import org.example.organism.animal.predator.Wolf;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        AppConfigurator.getInstance().init();

        GameObjectPrototypeFactory factory = GameObjectPrototypeFactory.getInstance();

        Set<GameObject> gameObjects = new HashSet<>();

        gameObjects.add(factory.createPrototype(Horse.class));
        gameObjects.add(factory.createPrototype(Wolf.class));

        GameWorker gameWorker = new GameWorker(gameObjects);

        gameWorker.play();

    }
}