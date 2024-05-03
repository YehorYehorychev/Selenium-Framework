package org.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class myFirstTestCase {
    @Test
    public void dummyTest() {
        WebDriver driver = new ChromeDriver();
    }
}
