package org.example.config.enums;

import lombok.Getter;
import org.example.entity.map.GameMap;
import org.example.entity.organism.animal.herbivore.*;
import org.example.entity.organism.animal.predator.*;
import org.example.entity.organism.plant.Grass;

@Getter
public enum PrototypeYamlPath {
    //Herbivore
    BOAR(Boar.class,"organism/animals/herbivore/boar.yaml"),
    BUFFALO(Buffalo.class, "organism/animals/herbivore/buffalo.yaml"),
    CATERPILLAR(Caterpillar.class, "organism/animals/herbivore/caterpillar.yaml"),
    DEER(Deer.class, "organism/animals/herbivore/deer.yaml"),
    DUCK(Duck.class, "organism/animals/herbivore/duck.yaml"),
    GOAT(Goat.class, "organism/animals/herbivore/goat.yaml"),
    HORSE(Horse.class, "organism/animals/herbivore/horse.yaml"),
    MOUSE(Mouse.class, "organism/animals/herbivore/mouse.yaml"),
    RABBIT(Rabbit.class, "organism/animals/herbivore/rabbit.yaml"),
    SHEEP(Sheep.class, "organism/animals/herbivore/sheep.yaml"),
    //Predator
    BEAR(Bear.class, "organism/animals/predator/bear.yaml"),
    BOA(Boa.class, "organism/animals/predator/boa.yaml"),
    EAGLE(Eagle.class, "organism/animals/predator/eagle.yaml"),
    FOX(Fox.class, "organism/animals/predator/fox.yaml"),
    WOLF(Wolf.class, "organism/animals/predator/wolf.yaml"),
    //Plant
    GRASS(Grass.class, "organism/plants/grass.yaml"),

    //GameMap
    GAMEMAP(GameMap.class, "organism/map/map.yaml");

    private final Class<?> organismClass;
    private final String yamlFilePath;

    PrototypeYamlPath(Class<?> organismClass, String yamlFilePath) {
        this.organismClass = organismClass;
        this.yamlFilePath = yamlFilePath;
    }
}
