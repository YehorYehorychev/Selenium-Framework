package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {
    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage enterTextInSearchField(String product) {
        WebElement searchFieldElement = waitForElementToBeVisible(searchField);
        searchFieldElement.clear();
        searchFieldElement.sendKeys(product);
        return this;
    }

    public StorePage search(String product) {
        enterTextInSearchField(product);
        clickSearchButton();
        return this;
    }

    private void clickSearchButton() {
        waitForElementToBeClickable(searchButton).click();
    }

    public String getTitle() {
        return waitForElementToBeVisible(title).getText();
    }

    private By getAddToCartButtonElement(String productName) {
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public void clickAddToCartButton(String productName) {
        By addToCartButton = getAddToCartButtonElement(productName);
        waitForElementToBeClickable(addToCartButton).click();
    }

    public CartPage clickViewCart() {
        waitForElementToBeClickable(viewCartLink).click();
        return new CartPage(driver);
    }
}
