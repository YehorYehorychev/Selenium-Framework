package org.selenium.pom.api.actions;

import org.selenium.pom.objects.UserData;
import org.selenium.pom.utils.FakerUtils;


public class DummyClass {

    public static void main(String[] args) {
        String login = "demoqa" + new FakerUtils().generateRandomNumber();
        UserData userData = new UserData().
                setLogin(login).
                setPassword("demopwd").
                setEmail(login + "@gmail.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(userData);
        System.out.println("REGISTER COOKIES: " + signUpApi.getCookies());
        CartApi cartApi = new CartApi(signUpApi.getCookies());;;
        cartApi.addToCart(1215, 1);
        System.out.println(cartApi.getCookies());
    }
}
