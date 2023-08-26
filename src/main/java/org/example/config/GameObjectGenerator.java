package org.example.config;

import lombok.Getter;
import org.example.utils.Probably;
import org.example.entity.map.Cell;
import org.example.factory.GameObjectPrototypeFactory;
import org.example.factory.PrototypeFactory;
import org.example.gameObjects.GameObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class GameObjectGenerator {
    private static final PrototypeFactory<GameObject> prototypeFactory = GameObjectPrototypeFactory.getInstance();
    @Getter
    private static final GameObjectGenerator instance = new GameObjectGenerator();

    private GameObjectGenerator() {
    }

    public Map<Class<? extends GameObject>, Set<GameObject>> generateGameObjects(Cell cell) {
        Map<Class<? extends GameObject>, Set<GameObject>> residents = new HashMap<>();

        for (GameObject prototype : prototypeFactory.getPrototypes()) {
            residents.computeIfAbsent(prototype.getClass(), k -> new HashSet<>());
            Set<GameObject> gameObjectFamilyContainer = residents.get(prototype.getClass());
            double randomAmount = Probably.randomInt(0, prototype.getLimits().getMaxAmount());
            for (int i = 0; i < randomAmount; i++) {
                GameObject newObject = prototype.reproduce();
                newObject.setGameObjectCell(cell);
                gameObjectFamilyContainer.add(newObject);
            }
        }
        return residents;
    }
}

