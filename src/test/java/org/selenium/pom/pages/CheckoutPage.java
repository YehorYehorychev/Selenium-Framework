package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.UserData;

import java.time.Duration;
import java.util.List;

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

    private final By clickHereToLoginLink = By.cssSelector(".showlogin");
    private final By usernameFiled = By.cssSelector("#username");
    private final By passwordFiled = By.cssSelector("#password");
    private final By loginButton = By.cssSelector("button[value='Login']");
    private final By overlay = By.cssSelector(".blockUI,blockOverlay");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName) {
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        driver.findElement(addressLineOneField).clear();
        driver.findElement(addressLineOneField).sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        driver.findElement(billingCityField).clear();
        driver.findElement(billingCityField).sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        driver.findElement(billingPostCodeField).clear();
        driver.findElement(billingPostCodeField).sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        driver.findElement(billingEmailField).clear();
        driver.findElement(billingEmailField).sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                enterPostCode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());
    }

    public CheckoutPage setUserCredentials(UserData userData) {
        return enterUserCredentials(userData.getLogin(), userData.getPassword());
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderButton).click();
        return this;
    }

    public String getNotice() {
        return driver.findElement(successNotice).getText();
    }

    public CheckoutPage clickHereToLoginLink() {
        driver.findElement(clickHereToLoginLink).click();
        return this;
    }

    public CheckoutPage enterUserName(String username) {
        driver.findElement(usernameFiled).clear();
        driver.findElement(usernameFiled).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        driver.findElement(passwordFiled).clear();
        driver.findElement(passwordFiled).sendKeys(password);
        return this;
    }

    public CheckoutPage enterUserCredentials(String login, String password) {
        driver.findElement(usernameFiled).clear();
        driver.findElement(passwordFiled).clear();
        driver.findElement(usernameFiled).sendKeys(login);
        driver.findElement(passwordFiled).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return this;
    }

    public CheckoutPage login(String username, String password) {
        return enterUserName(username).
                enterPassword(password).
                clickLoginButton();
    }
}
