package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {

    @FindBy(id = "woocommerce-product-search-field-0")
    private WebElement searchField;

    @FindBy(css = "button[value='Search']")
    private WebElement searchButton;

    @FindBy(css = ".woocommerce-products-header__title.page-title")
    private WebElement title;

    @FindBy(css = "a[title='View cart']")
    private WebElement viewCartLink;

    public StorePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public StorePage enterTextInSearchField(String product) {
        waitForElementToBeVisible(searchField);
        searchField.clear();
        searchField.sendKeys(product);
        return this;
    }

    public StorePage load() {
        load("/store");
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

    public StorePage clickAddToCartButton(String productName) {
        By addToCartButton = getAddToCartButtonElement(productName);
        waitForElementToBeClickableUsingBy(addToCartButton).click();
        return this;
    }

    public CartPage clickViewCart() {
        waitForElementToBeClickable(viewCartLink).click();
        return new CartPage(driver);
    }

    public Boolean isLoaded() {
        return waitForUrlToContain("/store");
    }
}
