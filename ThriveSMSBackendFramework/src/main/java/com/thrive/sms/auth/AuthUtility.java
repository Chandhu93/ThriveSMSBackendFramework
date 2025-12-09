 package com.thrive.sms.auth;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthUtility {

    public static String getToken() {
        Response res = RestAssured
            .given()
                .formParam("grant_type", "")
                .formParam("username", "")
                .formParam("password", "")
                .formParam("client_id", "")
                .formParam("client_secret", "")
            .when()
                .post("https://your-auth-server.com/oauth/token");

        return res.jsonPath().get("access_token");
    }
}

