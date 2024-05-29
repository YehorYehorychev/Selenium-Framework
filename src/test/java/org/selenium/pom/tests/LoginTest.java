package org.selenium.pom.tests;

import io.restassured.http.Cookies;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.UserData;
import org.selenium.pom.pages.AccountPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @Test
    public void loginDuringCheckout() throws Exception {
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        UserData user = new UserData().
                setLogin(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(new Cookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        checkoutPage.
                clickHereToLoginLink().
                login(user);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }

    @Test()
    public void shouldNotLoginWithAnInvalidPassword(){
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        UserData user = new UserData(username, "demopwd", username + "@askomdch.com");
        new SignUpApi().register(user);

        AccountPage accountPage = new AccountPage(getDriver()).load();
        accountPage.login(user.getLogin(), "invalidPassword");
        Assert.assertEquals(accountPage.getErrorTxt(), "Error: The password you entered for the username "
                + user.getLogin() + " is incorrect. Lost your password?");
    }

    @Test()
    public void shouldNotLoginWithANonExistingUser(){
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        UserData user = new UserData(username, "demopwd", username + "@askomdch.com");

        AccountPage accountPage = new AccountPage(getDriver()).load();
        accountPage.login(user.getLogin(), "demopwd");
        Assert.assertEquals(accountPage.getErrorTxt(), "Error: The username " + user.getLogin() +
                " is not registered on this site." +
                " If you are unsure of your username, try your email address instead.");
    }
}
