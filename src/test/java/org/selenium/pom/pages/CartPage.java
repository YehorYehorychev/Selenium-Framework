package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {
    private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutButton = By.cssSelector(".checkout-button");
    private final By cartHeading = By.cssSelector(".has-text-align-center");

    public CartPage(WebDriver driver) {
        super(driver);
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
