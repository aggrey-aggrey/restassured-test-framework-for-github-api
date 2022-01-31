package com.aggrey.github.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {


    public static RequestSpecification getRequestSpec (){
        return new RequestSpecBuilder().
                setBaseUri("https://mygithubapimock.free.beeceptor.com").
                //setBasePath(Route.BASE_PATH).
                setContentType(ContentType.JSON).log(LogDetail.ALL).build();
    }

    public static ResponseSpecification getResponseSpec (){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).build();
    }

}
