package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {
    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load() {
        load("/");
        return this;
    }

    public StorePage navigateToStoreUsingMenu() {
        waitForElementToBeClickable(storeMenuLink).click();
        return new StorePage(driver);
    }
}
