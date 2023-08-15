package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.example.exeption.InitGameException;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PrototypeLoader {
    private static PrototypeLoader instance;
    private final ObjectMapper objectMapper = new YAMLMapper();

    public static PrototypeLoader getInstance() {
        if (instance == null) {
            instance = new PrototypeLoader();
        }
        return instance;
    }

    public <T> T loadPrototype(Class<T> type) {
        PrototypeYamlPath yamlPathEnum = getEnumByClass(type);
        if (yamlPathEnum == null) {
            throw new InitGameException("Yaml path not found for class: " + type);
        }

        return loadObject(yamlPathEnum.getYamlFilePath(), type);
    }

    private <T> PrototypeYamlPath getEnumByClass(Class<T> type) {
        for (PrototypeYamlPath enumValue : PrototypeYamlPath.values()) {
            if (enumValue.getOrganismClass().equals(type)) {
                return enumValue;
            }
        }
        return null;
    }
    private <T> T loadObject(String configFilePath, Class<T> type) {
        T gameObject;
        URL url = stringToURL(configFilePath);

        try {
            gameObject = objectMapper.readValue(configFilePath, type);
        } catch (IOException e) {
            String message = String.format("Cannot find config file %s for class %s",
                    url.getFile(),
                    type);
            throw new InitGameException(message, e);
        }

        return gameObject;
    }
    public URL stringToURL(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            // Обробка винятку, якщо URL некоректний
            e.printStackTrace();
            return null;
        }
    }
}
