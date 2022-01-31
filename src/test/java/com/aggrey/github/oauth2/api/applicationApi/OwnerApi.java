package com.aggrey.github.oauth2.api.applicationApi;

import com.aggrey.github.oauth2.api.RestResources;
import com.aggrey.github.oauth2.api.Route;
import io.restassured.response.Response;

import java.io.IOException;

public class OwnerApi {

    public  static Response post (OwnerApi requestOwner) throws IOException {
        return RestResources.post(Route.USERS + "/aggrey-aggrey" + Route.PLAYLISTS, RestResources.getAccess_token(), requestOwner);
    }

    public  static Response post (String token, OwnerApi requestOwner) throws IOException {
        return  RestResources.post(Route.USERS  + "/aggrey-aggrey" + Route.PLAYLISTS, token,requestOwner );
    }

    public  static Response get(String commentId) throws IOException {
        return RestResources.get(Route.PLAYLISTS + "/" + commentId,  RestResources.getAccess_token());

    }

    public  static Response get() throws IOException {
        return RestResources.get(Route.USERS + "/aggrey-aggrey" ,  RestResources.getAccess_token());
    }


    public  static Response update (String playlistId, OwnerApi requestOwner) throws IOException {
        return  RestResources.update(Route.PLAYLISTS + "/" + playlistId, RestResources.getAccess_token(), requestOwner);

    }
}
