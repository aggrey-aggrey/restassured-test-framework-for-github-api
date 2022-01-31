package com.aggrey.github.oauth2.api;

import com.aggrey.github.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestResources {

   static String token = "1234";

    public static String getAccess_token() {
        return token;
    }

    public  static Response post (String path, String token, Object requestPayload){
        return given(SpecBuilder.getRequestSpec()).


                body(requestPayload).
                header("Authorization", token).
                when().post(path).
                then().spec(SpecBuilder.getResponseSpec())
                .extract().
                response();

    }

    public  static Response get(String path, String token){
        return given(SpecBuilder.getRequestSpec()).
                header("Authorization", token).
                when().get(path).
                then().spec(SpecBuilder.getResponseSpec()).
                extract().
                response();

    }

    public  static Response patch (String path, String token, Object requestPayload){
        return given(SpecBuilder.getRequestSpec()).
                body(requestPayload).
                header("Authorization", token).
                when().patch(path).
                then().spec(SpecBuilder.getResponseSpec())
                .extract().
                        response();
    }

    public  static Response update (String path, String token, Object requestPayload){
        return  given(SpecBuilder.getRequestSpec()).
                body(requestPayload).
                header("Authorization", token).
                when().put(path).
                then().spec(SpecBuilder.getResponseSpec()).
                extract().
                response();

        }
}
