package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {

    @FindBy(css = "#menu-item-1227 > a")
    private WebElement storeMenuLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage load() {
        load("/");
//        waitForTitleToContain("AskOmDch"); // Uncomment if title check is needed
        return this;
    }

    public StorePage navigateToStoreUsingMenu() {
        waitForElementToBeClickable(storeMenuLink).click();
        return new StorePage(driver);
    }
}
