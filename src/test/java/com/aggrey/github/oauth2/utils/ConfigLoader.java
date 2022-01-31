package com.aggrey.github.oauth2.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() throws IOException {
        properties = PropertyLoader.propertyLoader("src/test/resources/config.properties");
    }


    public static ConfigLoader getInstance() throws IOException {
        if (configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public  String getClientId(){
        String prop = properties.getProperty("client_id");
        if(prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public  String getClientSecret() {
        String prop = properties.getProperty("client_secret");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_secret is not specified in the config.properties file");
    }

    public  String getClientCredentials() {
        String prop = properties.getProperty("client_credentials");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_credentials is not specified in the config.properties file");
    }

    public  String getGrantType() {
        String prop = properties.getProperty("grant_type");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_secret is not specified in the config.properties file");
    }

    public  String getUserId() {
        String prop = properties.getProperty("user_id");
        if (prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }

    public  String getAccessToken() {
        String prop = properties.getProperty("access_token");
        if (prop != null) return prop;
        else throw new RuntimeException("property access_token is not specified in the config.properties file");
    }

    public  String getUser() {
        String prop = properties.getProperty("user");
        if (prop != null) return prop;
        else throw new RuntimeException("property user is not specified in the config.properties file");
    }

    public  String getRepo() {
        String prop = properties.getProperty("repo");
        if (prop != null) return prop;
        else throw new RuntimeException("property repo is not specified in the config.properties file");
    }

}
