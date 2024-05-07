package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {
    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By addToCartButton = By.cssSelector("a[aria-label='Add ＂Blue Shoes＂ to your cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public void enterTextInSearchField() {

    }
}
