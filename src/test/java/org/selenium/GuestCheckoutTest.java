package org.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class GuestCheckoutTest extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() {
        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.clickStoreMenuLink();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        storePage.clickAddToCartButton("Blue Shoes");
        storePage.clickViewCart();


        navigateToProduct();
        searchForProduct("Blue");
        verifySearchResults("Blue");

        addToCart("Blue Shoes");
        goToCart();
        verifyProductInCart("Blue Shoes");

        proceedToCheckout();
        fillBillingDetails();
        placeOrder();
        verifyOrderConfirmation();
    }

    @Test
    public void loginToExistingAccountAndCheckoutUsingDirectBankTransfer() {
        navigateToProduct();
        searchForProduct("Blue");
        verifySearchResults("Blue");

        addToCart("Blue Shoes");
        goToCart();
        verifyProductInCart("Blue Shoes");

        proceedToCheckout();
        loginToAccountFromCheckoutPage();
        fillBillingDetails();
        placeOrder();
        verifyOrderConfirmation();
    }

    // ############################################  METHODS  ############################################

    private void navigateToProduct() {
        driver.findElement(By.cssSelector("#menu-item-1227 > a")).click();
    }

    private void searchForProduct(String productName) {
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys(productName);
        driver.findElement(By.cssSelector("button[value='Search']")).click();
    }

    private void verifySearchResults(String expectedTitle) {
        String actualTitle = driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText();
        Assert.assertEquals(actualTitle, "Search results: “" + expectedTitle + "”");
    }

    private void addToCart(String productName) {
        driver.findElement(By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']")).click();
    }

    private void goToCart() {
        WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='View cart']")));
        viewCartButton.click();
    }

    private void verifyProductInCart(String expectedProductName) {
        String actualProductName = driver.findElement(By.cssSelector("td[class='product-name'] a")).getText();
        Assert.assertEquals(actualProductName, expectedProductName);
    }

    private void proceedToCheckout() {
        driver.findElement(By.cssSelector(".checkout-button")).click();
    }

    private void loginToAccountFromCheckoutPage() {
        driver.findElement(By.cssSelector(".showlogin")).click();
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#username")));
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#password")));
        login.sendKeys("yehor");
        password.sendKeys("yehor123");
        driver.findElement(By.cssSelector("button[value='Login']")).click();
    }

    private void fillBillingDetails() {
        driver.findElement(By.cssSelector("#billing_first_name")).sendKeys("yehor");
        driver.findElement(By.cssSelector("#billing_last_name")).sendKeys("test");
        driver.findElement(By.cssSelector("#billing_company")).sendKeys("google");
        driver.findElement(By.cssSelector("#billing_address_1")).sendKeys("San Francisco");
        driver.findElement(By.cssSelector("#billing_city")).sendKeys("San Francisco");
        WebElement postcode = driver.findElement(By.cssSelector("#billing_postcode"));
        String currentPostcode = postcode.getAttribute("value");
        if (!"94040".equals(currentPostcode)) {
            postcode.clear();
            postcode.sendKeys("94040");
        }
        WebElement email = driver.findElement(By.cssSelector("#billing_email"));
        if (!"yehor@test.com".equals(email.getAttribute("value"))) {
            email.sendKeys("yehor@test.com");
        }
    }

    private void placeOrder() {
        WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#place_order")));
        try {
            placeOrderButton.click();
        } catch (StaleElementReferenceException e) {
            placeOrderButton = driver.findElement(By.cssSelector("#place_order"));
            placeOrderButton.click();
        }
    }

    private void verifyOrderConfirmation() {
        WebElement wooNotice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-notice")));
        Assert.assertEquals(wooNotice.getText(), "Thank you. Your order has been received.");
    }
}
