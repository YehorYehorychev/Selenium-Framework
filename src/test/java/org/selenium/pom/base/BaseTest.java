package org.selenium.pom.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.*;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser) {
//        browser = System.getProperty("browser", browser);
        if (browser == null) browser = "CHROME";
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("Current Thread: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
    }

    @AfterMethod
    public void quitDriver() {
        getDriver().quit();
    }

    @AfterClass
    public void tearDownClass() {
        WebDriverManager.chromedriver().clearDriverCache();
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }
}
