package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.pom.base.BasePage;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//button[@class='single_add_to_cart_button button alt']")
    private WebElement addToCartBtn;

    @FindBy(css = ".product_title.entry-title")
    private WebElement productTitle;

    @FindBy(css = "div[role='alert']")
    private WebElement alert;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getProductTitle() {
        return waitForElementToBeVisible(productTitle).getText();
    }

    public ProductPage addToCart() {
        addToCartBtn.click();
        return this;
    }

    public ProductPage loadProduct(String productNameSeparatedByDash) {
        load("/product/" + productNameSeparatedByDash + "/");
        return this;
    }

    public String getAlert() {
        return waitForElementToBeVisible(alert).getText();
    }
}

