package com.aggrey.github.oauth2.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    public static Properties propertyLoader(String filePath) throws IOException {
        Properties properties = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("properties file not found at  " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("failed to load properties file " + filePath);

        }

        return properties;

    }
}