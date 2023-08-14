package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.example.gameObjects.GameObject;

import java.io.IOException;
import java.net.URL;

public class PrototypeLoader {
    private static PrototypeLoader instance;
    private final ObjectMapper objectMapper = new YAMLMapper();
    private PrototypeLoader() {
    }

    public static PrototypeLoader getInstance() {
        if (instance == null) {
            instance = new PrototypeLoader();
        }
        return instance;
    }
    private <T> T loadObject(Class<T> type) throws Exception {
        T gameObject;

        try {
            gameObject = objectMapper.readValue(configFilePath, type);
        } catch (IOException e) {
            String message = String.format("Cannot find config file %s for class %s",
                    configFilePath.getFile(),
                    type);
            throw new Exception(message, e);
        }

        return gameObject;
    }
    private URL getConfigFilePath(Class<?> type) {
        config =
        return type.getClassLoader().getResource(config.fileName());
    }
}
