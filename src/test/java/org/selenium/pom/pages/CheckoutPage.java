package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.UserData;

import java.util.List;


public class CheckoutPage extends BasePage {

    @FindBy(css = "#billing_first_name")
    private WebElement firstNameField;

    @FindBy(css = "#billing_last_name")
    private WebElement lastNameField;

    @FindBy(css = "#billing_address_1")
    private WebElement addressLineOneField;

    @FindBy(css = "#billing_city")
    private WebElement billingCityField;

    @FindBy(css = "#billing_company")
    private WebElement billingCompanyField;

    @FindBy(css = "#billing_postcode")
    private WebElement billingPostCodeField;

    @FindBy(css = "#billing_email")
    private WebElement billingEmailField;

    @FindBy(css = "#place_order")
    private WebElement placeOrderButton;

    @FindBy(css = ".woocommerce-notice")
    private WebElement successNotice;

    @FindBy(css = ".showlogin")
    private WebElement clickHereToLoginLink;

    @FindBy(xpath = "//a[text()='Lost your password?']")
    private WebElement lostYourPasswordButton;

    @FindBy(css = "#username")
    private WebElement usernameFiled;

    @FindBy(css = "#password")
    private WebElement passwordFiled;

    @FindBy(css = ".blockUI.blockOverlay")
    private List<WebElement> overlay;

    @FindBy(id = "billing_country")
    private WebElement countryDropDown;

    @FindBy(id = "billing_state")
    private WebElement stateDropDown;

    @FindBy(id = "payment_method_bacs")
    private WebElement directBankTransferRadioButton;

    @FindBy(css = "button[value='Login']")
    private WebElement loginButton;

    @FindBy(css = "td[class='product-name']")
    private WebElement productName;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage load() {
        load("/checkout");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        waitForElementToBeVisible(firstNameField);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        waitForElementToBeVisible(lastNameField);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) {
        Select select = new Select(waitForElementToBeVisible(countryDropDown));
        select.selectByVisibleText(countryName);
        return this;
        /*
         * If an element in the DOM is not immediately visible,
         * and you need to scroll for it to appear in the DOM of the page, use this ->
         * WebElement e = waitForElementToBeClickable(By.Xpath("Xpath"));
         * ((JavaScriptExecutor) driver). executeScript("arguments[0].scrollIntoView(true);", e);
         * e.click();
         */
    }

    public CheckoutPage selectState(String stateName) {
        Select select = new Select(waitForElementToBeVisible(stateDropDown));
        select.selectByVisibleText(stateName);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        waitForElementToBeVisible(addressLineOneField);
        addressLineOneField.clear();
        addressLineOneField.sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        waitForElementToBeVisible(billingCityField);
        billingCityField.clear();
        billingCityField.sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        waitForElementToBeVisible(billingPostCodeField);
        billingPostCodeField.clear();
        billingPostCodeField.sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        waitForElementToBeVisible(billingEmailField);
        billingEmailField.clear();
        billingEmailField.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName())
                .enterLastName(billingAddress.getLastName())
                .selectCountry(billingAddress.getCountry())
                .selectState(billingAddress.getState())
                .enterAddressLineOne(billingAddress.getAddressLineOne())
                .enterCity(billingAddress.getCity())
                .enterPostCode(billingAddress.getPostalCode())
                .enterEmail(billingAddress.getEmail());
    }

    public CheckoutPage setUserCredentials(UserData userData) {
        return enterUserCredentials(userData.getLogin(), userData.getPassword());
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear(overlay);
        waitForElementToBeClickable(placeOrderButton).click();
        return this;
    }

    public String getNotice() {
        return waitForElementToBeVisible(successNotice).getText();
    }

    public CheckoutPage clickHereToLoginLink() {
        waitForElementToBeClickable(clickHereToLoginLink).click();
        waitForElementToBeVisible(lostYourPasswordButton);
        waitForElementToBeClickable(lostYourPasswordButton);
        return this;
    }

    public CheckoutPage enterUserName(String username) {
        waitForElementToBeClickable(usernameFiled);
        usernameFiled.clear();
        usernameFiled.sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        waitForElementToBeClickable(passwordFiled);
        passwordFiled.clear();
        passwordFiled.sendKeys(password);
        return this;
    }

    public CheckoutPage enterUserCredentials(String login, String password) {
        enterUserName(login);
        enterPassword(password);
        return this;
    }

    public CheckoutPage clickLoginButton() {
        waitForElementToBeVisible(loginButton);
        waitForElementToBeClickable(loginButton).click();
        return this;
    }

    public CheckoutPage login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
        return this;
    }

    public CheckoutPage selectDirectBankTransfer() {
        waitForElementToBeClickable(directBankTransferRadioButton);
        if (!directBankTransferRadioButton.isSelected()) {
            directBankTransferRadioButton.click();
        }
        return this;
    }

    public String getProductName() {
        return waitForElementToBeVisible(productName).getText();
    }
}
