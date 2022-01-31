package com.aggrey.github.oauth2.api.applicationApi;

import com.aggrey.github.oauth2.api.RestResources;
import com.aggrey.github.oauth2.api.Route;
import com.aggrey.github.oauth2.pojo.Issue;
import com.aggrey.github.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import java.io.IOException;


public class IssueEndPoint {


    public  static Response post (String owner, String userRepo, Issue requestPayload) throws IOException {
        return RestResources.post(Route.REPOS + "/" + owner + "/" + userRepo  + Route.ISSUES, RestResources.getAccess_token(), requestPayload);
    }

    public  static Response get(String userRepo, String owner, int issueId) throws IOException {
        return RestResources.get(Route.REPOS + "/" + owner +  "/" +  userRepo +  Route.ISSUES  + "/" + issueId,  RestResources.getAccess_token());
    }

    public  static Response patch (String owner, String userRepo, int issuerId,  Issue requestPayload) throws IOException {
        return  RestResources.patch(Route.REPOS  + "/" + owner  + "/" + userRepo+ Route.ISSUES + "/" +  issuerId , RestResources.getAccess_token(), RestResources.getAccess_token());
    }


    public  static Response get() throws IOException {
        return RestResources.get(Route.USERS + "/" + ConfigLoader.getInstance().getUser() + "/",  RestResources.getAccess_token());
    }


    public  static Response update (String playlistId, OwnerApi requestOwner) throws IOException {
        return  RestResources.update(Route.PLAYLISTS + "/" + playlistId, RestResources.getAccess_token(), requestOwner);

    }


}
