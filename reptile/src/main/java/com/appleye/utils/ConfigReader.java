package com.appleye.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    public static Properties getProperties(String path){
        Properties properties = new Properties();
        InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(path);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
