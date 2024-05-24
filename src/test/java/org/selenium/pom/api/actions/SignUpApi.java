package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.selenium.pom.utils.ConfigLoader;

import static io.restassured.RestAssured.given;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    private void getAccount() {
        Response response =
         given().
                baseUri(ConfigLoader.getInstance().getBaseUrl()).
                cookies(getCookies()).
                 log().all().
         when().
                get("/account").
         then().
                log().all().
                extract().
                response();
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch the account " + response.getStatusCode());
        }
    }
}
