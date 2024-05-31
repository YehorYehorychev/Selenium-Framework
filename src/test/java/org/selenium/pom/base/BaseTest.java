package org.selenium.pom.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.factory.DriverManagerOriginal;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.pom.utils.CookieUtils;
import org.testng.annotations.*;

import java.util.List;

public class BaseTest {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser) {
        browser = System.getProperty("browser", browser);
//        if (browser == null) browser = "CHROME";
//        setDriver(new DriverManagerOriginal().initializeDriver(browser));
//        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
        setDriver(DriverManagerFactoryAbstract.
                getManager(DriverType.valueOf(browser)).getDriver());
        System.out.println("Current Thread: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
    }

    @AfterMethod
    public synchronized void quitDriver() {
        getDriver().quit();
    }

    @AfterClass
    public void tearDownClass() {
        WebDriverManager.chromedriver().clearDriverCache();
    }

    public void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie : seleniumCookies) {
            getDriver().manage().addCookie(cookie);
        }
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }
}
