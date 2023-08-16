package org.example.config.enums;

import lombok.Getter;
import org.example.entity.organism.animal.herbivore.Horse;
import org.example.entity.organism.animal.predator.Wolf;

@Getter
public enum PrototypeYamlPath {
    HORSE(Horse.class, "organism/animals/herbivore/horse.yaml"),
    WOLF(Wolf.class, "organism/animals/predator/wolf.yaml");

    private final Class<?> organismClass;
    private final String yamlFilePath;

    PrototypeYamlPath(Class<?> organismClass, String yamlFilePath) {
        this.organismClass = organismClass;
        this.yamlFilePath = yamlFilePath;
    }
}