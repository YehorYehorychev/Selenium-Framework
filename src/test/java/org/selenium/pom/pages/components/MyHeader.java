package org.selenium.pom.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;

public class MyHeader extends BasePage {
    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");

    public MyHeader(WebDriver driver) {
        super(driver);
    }

    @Step
    public StorePage navigateToStoreUsingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
        return new StorePage(driver);
    }
}
