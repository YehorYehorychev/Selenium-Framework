package org.selenium.pom.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(String browser) {
        setDriver(new DriverManager().initializeDriver(browser));
    }

    @AfterMethod
    public void quitDriver() {
        getDriver().quit();
    }

    @AfterClass
    public void tearDownClass() {
        WebDriverManager.chromedriver().clearDriverCache();
    }

    public WebDriver getDriver() {
        return this.driver.get();
    }

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }
}
