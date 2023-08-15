package org.example.config;

import org.example.factory.GameObjectPrototypeFactory;
import org.example.gameObjects.GameObject;

public class AppConfigurator {
    private static AppConfigurator instance;
    private final PrototypeLoader prototypeLoader = PrototypeLoader.getInstance();
    private final GameObjectPrototypeFactory gameObjectFactory = GameObjectPrototypeFactory.getInstance();

    private AppConfigurator() {
    }

    public static AppConfigurator getInstance() {
        if (instance == null) {
            instance = new AppConfigurator();
        }
        return instance;
    }

    public void init() {
        registerPrototypes();
        // TODO: implement gameField config loading.
        // TODO: implement gameField initialization with cells.
    }

    private void registerPrototypes() {
        for (PrototypeYamlPath path : PrototypeYamlPath.values()) {
            Class<?> organismClass = path.getOrganismClass();
            GameObject prototype = (GameObject) prototypeLoader.loadPrototype(organismClass);
            gameObjectFactory.registerPrototype(prototype);
        }
    }
}