package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnail;

import java.io.IOException;

public class HomePage extends BasePage {

    @FindBy(css = "#menu-item-1227 > a")
    private WebElement storeMenuLink;

    private final MyHeader myHeader;
    private final ProductThumbnail productThumbnail;

    @Step
    public MyHeader getMyHeader() {
        return myHeader;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public HomePage(WebDriver driver) {
        super(driver);
        myHeader = new MyHeader(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public ProductPage navigateToTheProduct(int id) throws IOException {
        driver.findElement(By.xpath("//h2[normalize-space()='" + new Product(id).getName() + "']")).click();
        return new ProductPage(driver);
    }

    @Step
    public HomePage load() {
        load("/");
//        waitForTitleToContain("AskOmDch"); // Uncomment if title check is needed
        return this;
    }
}
