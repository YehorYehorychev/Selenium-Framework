package org.selenium.pom.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.util.List;

public class BaseTest {
    protected ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser) {
        browser = System.getProperty("browser", browser);
//        if (browser == null) browser = "CHROME";
//        setDriver(new DriverManagerOriginal().initializeDriver(browser));
//        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
        setDriverManager(DriverManagerFactoryAbstract.
                getManager(DriverType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
        System.out.println("Current Thread: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public synchronized void quitDriver(@Optional String browser, ITestResult result) {
//        getDriver().quit();
        if (result.getStatus() == ITestResult.FAILURE) {
            File destFile = new File("scr" + File.separator + browser + File.separator +
                    result.getTestClass().getRealClass().getSimpleName() + "_" + result.getMethod().getMethodName() + ".png");
        }
        getDriverManager().getDriver().quit();
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

    protected DriverManagerAbstract getDriverManager() {
        return this.driverManager.get();
    }

    private void setDriverManager(DriverManagerAbstract driverManager) {
        this.driverManager.set(driverManager);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }
}
