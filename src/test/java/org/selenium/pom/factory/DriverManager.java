package org.selenium.pom.factory;

import org.openqa.selenium.WebDriver;

public interface DriverManager {
    void createDriver();
    void quitDriver();
}
