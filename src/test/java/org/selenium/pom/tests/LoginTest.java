package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.UserData;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws IOException {
        String username = "demoqa" + new FakerUtils().generateRandomNumber();
        UserData userData = new UserData().setLogin(username).setPassword("demopwd").setEmail(username + "@gmail.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(userData);
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.
                load().
                clickHereToLoginLink().
                setUserCredentials(userData);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }
}
