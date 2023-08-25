package org.example.factory;

import lombok.Getter;
import org.example.gameObjects.GameObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Getter
public class GameObjectPrototypeFactory implements PrototypeFactory<GameObject> {
    private static GameObjectPrototypeFactory instance;
    private final Map<Class<? extends GameObject>, GameObject> prototypes = new HashMap<>();

    private GameObjectPrototypeFactory() {
    }

    public static GameObjectPrototypeFactory getInstance() {
        if (instance == null) {
            instance = new GameObjectPrototypeFactory();
        }
        return instance;
    }
    @Override
    public void registerPrototype(GameObject prototype) {
        prototypes.put(prototype.getClass(), prototype);
    }

    @Override
    public GameObject createPrototype(Class<? extends GameObject> type) {
        if (!prototypes.containsKey(type)) {
            throw new IllegalArgumentException();
        }

        return prototypes.get(type).reproduce();
    }
    @Override
    public Collection<GameObject> getPrototypes() {
        return prototypes.values();
    }
}