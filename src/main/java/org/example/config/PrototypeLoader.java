package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exeption.InitGameException;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PrototypeLoader {
    private static PrototypeLoader instance;
    private final Map<Class<?>, PrototypeYamlPath> classToYamlPathMap = new HashMap<>();

    private PrototypeLoader() {
        for (PrototypeYamlPath enumValue : PrototypeYamlPath.values()) {
            classToYamlPathMap.put(enumValue.getOrganismClass(), enumValue);
        }
    }

    public static PrototypeLoader getInstance() {
        if (instance == null) {
            instance = new PrototypeLoader();
        }
        return instance;
    }

    public <T> T loadPrototype(Class<T> type) {
        PrototypeYamlPath yamlPathEnum = classToYamlPathMap.get(type);
        if (yamlPathEnum == null) {
            throw new InitGameException("Yaml path not found for class: " + type);
        }

        return loadObject(yamlPathEnum.getYamlFilePath(), type);
    }

    private <T> T loadObject(String yamlPath, Class<T> type) {
        ObjectMapper objectMapper = new ObjectMapper();
        T organism;
        try {
            URL resource = getClass().getClassLoader().getResource(yamlPath);
            if (resource == null) {
                throw new InitGameException("Config file not found: " + yamlPath);
            }

            organism = objectMapper.readValue(resource, type);
        } catch (IOException e) {
            String message = String.format("Error reading config file %s for class %s",
                    yamlPath, type);
            throw new InitGameException(message, e);
        }

        return organism;
    }
}