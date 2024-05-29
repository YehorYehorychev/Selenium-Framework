package org.selenium.pom.utils;

import org.selenium.pom.constants.EnvTypes;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(EnvTypes.STAGING));
        switch (EnvTypes.valueOf(env)) {
            case STAGING ->
                    properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
            case PRODUCTION ->
                    properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
            default ->
                    throw new IllegalStateException("Invalid env type " + env);
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) return prop;
        else throw new RuntimeException("Property baseUrl is not specified in the stg_config.properties file");
    }
/*    public String getLogin() {
        String prop = properties.getProperty("login");
        if (prop != null) return prop;
        else throw new RuntimeException("Property login is not specified in the stg_config.properties file");
    }

    public String getPassword() {
        String prop = properties.getProperty("password");
        if (prop != null) return prop;
        else throw new RuntimeException("Property password is not specified in the stg_config.properties file");
    }*/
}
