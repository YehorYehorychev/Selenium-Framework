package org.selenium.pom.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.UserData;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Ecommerce shop 2.0")
@Feature("Checkout process using different preconditions")
public class GuestCheckoutTest extends BaseTest {

    @Story("Guest checkout")
    @Description("Checkout using direct bank transfer using a guest account")
    @Test(description = "Should allow guest checkout using direct bank transfer after searching for a product")
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu().
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");

        storePage.getProductThumbnail().clickAddToCartButton(product.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Story("Existing account checkout")
    @Description("Checkout using direct bank transfer using a existing account")
    @Test(description = "Should allow login to existing account and checkout using direct bank transfer after searching for a product")
    public void loginToExistingAccountAndCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        UserData userData = JacksonUtils.deserializeJson("userCredentials.json", UserData.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu().
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
        storePage.getProductThumbnail().clickAddToCartButton(product.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();
        checkoutPage.
                setUserCredentials(userData).
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }
}
