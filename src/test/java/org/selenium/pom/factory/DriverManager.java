package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.DriverType;

public class DriverManager {
    /*
        To run your tests in specific browser run -> mvn clean test -Dbrowser=CHROME
        ---------------------------------------------------------------------------------------
        If you prefer to run your tests directly from the IDE instead of using "mvn clean test",
        you need to set the system property as follows ->

        For example: browser = System.getProperty("browser", browser);

        Run tests using MAVEN -> mvn clean test -Dbrowser=CHROME
        OR mvn clean test OR mvn clean test "-DsuiteFile=testng.xml"
        ---------------------------------------------------------------------------------------
        UPDATE: mvn clean test -Denv=STAGING
    */
    public WebDriver initializeDriver(String browser) {
        WebDriver driver;
        switch (DriverType.valueOf(browser)) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default -> throw new IllegalStateException("Invalid browser name: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }
}
