package com.telegram;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {



    public Properties getProperties(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot read file - " + fileName);
        }
    }
}
