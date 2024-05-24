package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.objects.UserData;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    private String fetchRegisterNonceValueUsingGroovy() {
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll { it.@name == 'woocommerce-register-nonce' }.@value");
    }

    private String fetchRegisterNonceValueUsingJsoup() {
        Response response = getAccount();
        Document document = Jsoup.parse(response.body().prettyPrint());
        Element element = document.selectFirst("#woocommerce-register-nonce");
        assert element != null;
        return element.attr("value");
    }

    private Response getAccount() {
        Response response =
                given()
                        .baseUri(ConfigLoader.getInstance().getBaseUrl())
                        .log().all()
                        .when()
                        .get("/account")
                        .then()
                        .log().all()
                        .extract()
                        .response();
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch the account " + response.getStatusCode());
        }
        return response;
    }

    public Response register(UserData userData) {
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
