package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CartApi {

    public Response addToCart() {
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("username", userData.getLogin());
        formParams.put("email", userData.getEmail());
        formParams.put("password", userData.getPassword());
        formParams.put("woocommerce-register-nonce", fetchRegisterNonceValueUsingJsoup());
        formParams.put("register", "Register");

        System.out.println("Form parameters: " + formParams);  // Log form parameters

        Response response =
                given()
                        .baseUri(ConfigLoader.getInstance().getBaseUrl())
                        .headers(headers)
                        .formParams(formParams)
                        .log().all()
                        .when()
                        .post("/account")
                        .then()
                        .log().all()
                        .extract()
                        .response();
        if (response.getStatusCode() != 302) {
            throw new RuntimeException("Failed to register the account " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
