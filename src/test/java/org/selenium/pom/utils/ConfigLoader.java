package org.selenium.pom.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
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
        else throw new RuntimeException("Property baseUrl is not specified in the config.properties file");
    }
/*    public String getLogin() {
        String prop = properties.getProperty("login");
        if (prop != null) return prop;
        else throw new RuntimeException("Property login is not specified in the config.properties file");
    }

    public String getPassword() {
        String prop = properties.getProperty("password");
        if (prop != null) return prop;
        else throw new RuntimeException("Property password is not specified in the config.properties file");
    }*/
}
