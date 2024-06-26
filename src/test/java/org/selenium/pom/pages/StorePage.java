package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.components.ProductThumbnail;

import java.io.IOException;

public class StorePage extends BasePage {

    @FindBy(id = "woocommerce-product-search-field-0")
    private WebElement searchField;

    @FindBy(css = "button[value='Search']")
    private WebElement searchButton;

    @FindBy(css = ".woocommerce-products-header__title.page-title")
    private WebElement title;

    @FindBy(css = "a[title='View cart']")
    private WebElement viewCartLink;

    @FindBy(css = ".woocommerce-info")
    private WebElement infoTxt;

    private final ProductThumbnail productThumbnail;

    @Step
    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
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

    @Step
    public StorePage search(String product) {
        enterTextInSearchField(product);
        clickSearchButton();
        return this;
    }

    public ProductPage searchExactMatch(String txt) {
        enterTextInSearchFld(txt).clickSearchBtn();
        return new ProductPage(driver);
    }

    private StorePage enterTextInSearchFld(String txt) {
        waitForElementToBeVisible(searchField).sendKeys(txt);
        return this;
    }

    public ProductPage navigateToTheProduct(Integer id) throws IOException {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h2[normalize-space()='" + new Product(id).getName() + "']"))).click();
        return new ProductPage(driver);
    }

    public String getInfo() {
        return waitForElementToBeVisible(infoTxt).getText();
    }

    private void clickSearchButton() {
        waitForElementToBeClickable(searchButton).click();
    }

    private StorePage clickSearchBtn() {
        waitForElementToBeClickable(searchButton).click();
        return this;
    }

    public String getTitle() {
        return waitForElementToBeVisible(title).getText();
    }

    public Boolean isLoaded() {
        return waitForUrlToContain("/store");
    }
}
