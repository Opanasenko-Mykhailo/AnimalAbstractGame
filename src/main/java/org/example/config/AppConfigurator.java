package org.example.config;

import lombok.ToString;
import org.example.engine.Simulation;
import org.example.entity.map.GameMap;
import org.example.factory.GameObjectPrototypeFactory;
import org.example.gameObjects.GameObject;

@ToString
public class AppConfigurator {
    private static AppConfigurator instance;
    private final GameObjectsScanner gameObjectsScanner = GameObjectsScanner.getInstance();
    private final PrototypeLoader prototypeLoader = PrototypeLoader.getInstance();
    private final GameObjectPrototypeFactory gameObjectFactory = GameObjectPrototypeFactory.getInstance();
    private final GameMapGenerator gameMapGenerator = GameMapGenerator.getInstance();
    private final CellGenerator cellGenerator = CellGenerator.getInstance();
    private final Simulation simulation = Simulation.getInstance();


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
        GameMap.setInstance(generateGameMap());
        simulation.startSimulation();

    }
    private void registerPrototypes() {
        gameObjectsScanner.
                getAllGameObjectsClasses()
                .stream()
                .map(prototypeLoader::loadPrototype)
                .forEach(prototype -> gameObjectFactory.registerPrototype((GameObject) prototype));
    }

    private GameMap generateGameMap() {
        return gameMapGenerator.createGameMap();
    }
}