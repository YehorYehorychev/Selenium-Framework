package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.UserData;


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
    private final By countryDropDown = By.id("billing_country");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName) {
        WebElement firstNameElement = waitForElementToBeVisible(firstNameField);
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        WebElement lastNameElement = waitForElementToBeVisible(lastNameField);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryname) {
        Select select = new Select(waitForElementToBeVisible(countryDropDown));
        select.selectByVisibleText(countryname);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        WebElement addressLineOneElement = waitForElementToBeVisible(addressLineOneField);
        addressLineOneElement.clear();
        addressLineOneElement.sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        WebElement cityElement = waitForElementToBeVisible(billingCityField);
        cityElement.clear();
        cityElement.sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        WebElement postCodeElement = waitForElementToBeVisible(billingPostCodeField);
        postCodeElement.clear();
        postCodeElement.sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        WebElement emailElement = waitForElementToBeVisible(billingEmailField);
        emailElement.clear();
        emailElement.sendKeys(email);
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
        waitForElementToBeClickable(placeOrderButton).click();
//        driver.findElement(placeOrderButton).click();
        return this;
    }

    public String getNotice() {
        return waitForElementToBeVisible(successNotice).getText();
    }

    public CheckoutPage clickHereToLoginLink() {
        waitForElementToBeClickable(clickHereToLoginLink).click();
        return this;
    }

    public CheckoutPage enterUserName(String username) {
        WebElement usernameElement = waitForElementToBeClickable(usernameFiled);
        usernameElement.clear();
        usernameElement.sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        WebElement passwordElement = waitForElementToBeClickable(passwordFiled);
        passwordElement.clear();
        passwordElement.sendKeys(password);
        return this;
    }

    public CheckoutPage enterUserCredentials(String login, String password) {
        enterUserName(login);
        enterPassword(password);
        return this;
    }

    public CheckoutPage clickLoginButton() {
        waitForElementToBeClickable(loginButton).click();
        return this;
    }

    public CheckoutPage login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
        return this;
    }
}
