package com.aggrey.github.oauth2.api;

import com.aggrey.github.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public static String getToken (){
        try {

            if (access_token == null || Instant.now().isAfter(expiry_time)) {
            System.out.println("Renewing token ....");
            Response response = renewToken();
            access_token = response.path("access_token");
            int expiryDurationInSeconds = response.path("expires_in");
            expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);

        }else{
            System.out.println("Token is good to use ....");
        }

        } catch(Exception e) {


            throw new RuntimeException("ABORT !!! Failed to get token");
        }
        return access_token;



}




    public static Response renewToken() throws IOException {
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParams.put("client_credentials", ConfigLoader.getInstance().getClientCredentials());
        formParams.put("grant_type", "client_credentials");

        Response response = given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParam(String.valueOf(formParams)).
                when().post(Route.API + Route.TOKEN).then().spec(SpecBuilder.getResponseSpec()).
                extract().response();

        if (response.statusCode() != 200){
            throw new RuntimeException("ABORT !!! Renew Token failed");
        }

        return response;
    }


}