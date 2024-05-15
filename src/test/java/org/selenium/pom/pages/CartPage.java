package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {
    private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutButton = By.cssSelector(".checkout-button");
    private final By cartHeading = By.cssSelector(".has-text-align-center");
//    I'm not sure that I want to implement PageFactory
//    @FindBy(css = "td[class='product-name'] a") private WebElement productName;
//    @FindBy(css = ".checkout-button") private WebElement checkoutButton;
//    @FindBy(how = How.CSS, using = ".has-text-align-center") @CacheLookup private WebElement cartHeading;

    public CartPage(WebDriver driver) {
        super(driver);
//        PageFactory.initElements(driver, this);
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
