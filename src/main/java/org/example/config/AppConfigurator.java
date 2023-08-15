package org.example.config;

import lombok.ToString;
import org.example.factory.GameObjectPrototypeFactory;
import org.example.gameObjects.GameObject;

import java.util.Set;
@ToString
public class AppConfigurator {
    private static AppConfigurator instance;
    private final GameObjectsScanner gameObjectsScanner = GameObjectsScanner.getInstance();
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
        gameObjectsScanner.
                getAllGameObjectsClasses()
                .stream()
                .map(prototypeLoader::loadPrototype)
                .forEach(prototype -> gameObjectFactory.registerPrototype((GameObject) prototype));
    }


}