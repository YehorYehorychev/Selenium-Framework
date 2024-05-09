package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class CheckoutPage extends BasePage {
    private final By firstNameField = By.cssSelector("#billing_first_name");
    private final By lastNameField = By.cssSelector("#billing_last_name");
    private final By addressLineOneField = By.cssSelector("#billing_address_1");
    private final By billingCityField = By.cssSelector("#billing_city");
    private final By billingCompanyField = By.cssSelector("#billing_company");
    private final By billingPostCodeField = By.cssSelector("#billing_postcode");
    private final By billingEmailField = By.cssSelector("#billing_email");
    private final By placeOrderButton = By.cssSelector("#place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(firstNameField).sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        driver.findElement(firstNameField).sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        driver.findElement(firstNameField).sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        driver.findElement(firstNameField).sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        driver.findElement(firstNameField).sendKeys(email);
        return this;
    }

}