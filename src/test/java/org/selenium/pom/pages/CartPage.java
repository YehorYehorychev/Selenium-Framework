package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {
    private final By cartHeading = By.cssSelector(".has-text-align-center");

    @FindBy(css = "td[class='product-name'] a")
    private WebElement productName;

    @FindBy(css = ".checkout-button")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Boolean isLoaded() {
        return waitForTextMatches(cartHeading, "Cart");
    }

    public String getProductName() {
        return waitForElementToBeVisible(productName).getText();
    }

    public CheckoutPage checkout() {
        waitForElementToBeClickable(checkoutButton).click();
        return new CheckoutPage(driver);
    }
}
