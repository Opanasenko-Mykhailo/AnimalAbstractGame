package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.config.enums.PrototypeYamlPath;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PrototypeLoader {
    private static PrototypeLoader instance;
    private PrototypeLoader() {
    }
    public static PrototypeLoader getInstance() {
        if (instance == null) {
            instance = new PrototypeLoader();
        }
        return instance;
    }

    public <T> T loadPrototype(Class<T> type) {
        URL configFilePath = getConfigFilePath(type);
        if (configFilePath != null) {
            return loadObject(configFilePath, type);
        }
        return null;
    }

    private URL getConfigFilePath(Class<?> type) {
        PrototypeYamlPath yamlPath = PrototypeYamlPath.valueOf(type.getSimpleName().toUpperCase());
        return getClass().getClassLoader().getResource(yamlPath.getYamlFilePath());
    }

    private <T> T loadObject(URL configFilePath, Class<T> type) {
        try (InputStream input = configFilePath.openStream()) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(input, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}