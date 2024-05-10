package org.selenium;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class GuestCheckoutTest extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setFirstName("Demo");
        billingAddress.setLastName("QA");
        billingAddress.setAddressLineOne("San Francisco");
        billingAddress.setCity("San Francisco");
        billingAddress.setPostalCode("94040");
        billingAddress.setEmail("yehor@test.com");

        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");

        storePage.clickAddToCartButton("Blue Shoes");
        Thread.sleep(2000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
                placeOrder();

        Thread.sleep(2000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginToExistingAccountAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setFirstName("Demo");
        billingAddress.setLastName("QA");
        billingAddress.setAddressLineOne("San Francisco");
        billingAddress.setCity("San Francisco");
        billingAddress.setPostalCode("94040");
        billingAddress.setEmail("yehor@test.com");

        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");

        storePage.clickAddToCartButton("Blue Shoes");
        Thread.sleep(2000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();
        Thread.sleep(2000);

        checkoutPage.
                login("yehor12a", "DemoPassword").
                setBillingAddress(billingAddress).
                placeOrder();

        Thread.sleep(2000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }
}
