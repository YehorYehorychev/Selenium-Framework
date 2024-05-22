package org.selenium.pom.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
//    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private WebDriver driver;

    /*@Parameters("browser")
    @BeforeMethod*/
    @Before
    public void startDriver() {
        String browser = System.getProperty("browser");
//        setDriver(new DriverManager().initializeDriver(browser));
//        System.out.println("Current Thread: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
        System.out.println("Current Thread: " + Thread.currentThread().getId() + ", " + "DRIVER = " + driver);
    }

//    @AfterMethod
    @After
    public void quitDriver() {
        driver.quit();
    }

//    @AfterClass
    @AfterClass
    public static void tearDownClass() {
        WebDriverManager.chromedriver().clearDriverCache();
    }
 /*   public void tearDownClass() {
        WebDriverManager.chromedriver().clearDriverCache();
    }*/

    /*protected WebDriver getDriver() {
        return this.driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }*/
}
