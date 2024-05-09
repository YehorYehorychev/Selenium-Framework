package org.selenium;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class GuestCheckoutTest extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        Thread.sleep(1000);

        storePage.clickAddToCartButton("Blue Shoes");
        Thread.sleep(1000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.
                enterFirstName("Demo").
                enterLastName("QA").
                enterAddressLineOne("San Francisco").
                enterCity("San Francisco").
                enterPostCode("94040").
                enterEmail("yehor@test.com").
                placeOrder();

        Thread.sleep(1000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginToExistingAccountAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        Thread.sleep(1000);

        storePage.clickAddToCartButton("Blue Shoes");
        Thread.sleep(2000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();
        Thread.sleep(1000);

        checkoutPage.
                login("yehor12a", "DemoPassword").
                enterFirstName("Demo").
                enterLastName("QA").
                enterAddressLineOne("San Francisco").
                enterCity("San Francisco").
                enterPostCode("94040").
                enterEmail("yehor12a@gmail.com").
                placeOrder();

        Thread.sleep(1000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }
}
