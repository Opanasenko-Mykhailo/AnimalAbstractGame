package org.example.config;

public enum ConfigYamlPathEnum {
    HORSE("entity/organism/animal/herbivore/horse.yaml"),
    WOLF("entity/organism/animal/predator/wolf.yaml"),
    // Додайте інші значення enum за необхідності
    ;

    private final String yamlPath;

    ConfigYamlPathEnum(String yamlPath) {
        this.yamlPath = yamlPath;
    }

    public String getYamlPath() {
        return yamlPath;
    }
}