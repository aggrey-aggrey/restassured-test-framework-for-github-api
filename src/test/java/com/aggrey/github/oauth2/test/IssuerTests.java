package com.aggrey.github.oauth2.test;

import com.aggrey.github.oauth2.api.applicationApi.OwnerApi;

import com.aggrey.github.oauth2.api.applicationApi.IssueEndPoint;
import com.aggrey.github.oauth2.pojo.Issue;
import com.aggrey.github.oauth2.pojo.Owner;
import com.aggrey.github.oauth2.pojo.Repository;
import com.aggrey.github.oauth2.pojo.User;
import io.restassured.response.Response;
import org.json.JSONException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import  com.aggrey.oauth2.pojo.Milestone;
import com.aggrey.github.oauth2.pojo.PullRequest;

public class IssuerTests {
    User user;
    Issue issue;
    Milestone milestone;
    PullRequest pullrequest;
    String gitHubRepoOwner;
    String gitHubRepoUrl;
    String gitHubRepoTitle;
    Issue issueTitle;

    public IssuerTests() throws IOException {
    }


    @BeforeClass
    public void beforeClass(){
        user = new User("aggrey-aggrey", 123,"https://mygithubapimock.free.beeceptor.com/users/aggrey-aggrey" );
        pullrequest = new PullRequest("https://mygithubapimock.free.beeceptor.com/repos/aggrey-aggrey/Hello-World/pulls/1347");
        issue =  new Issue("aggrey-aggrey",  pullrequest ,"https://mygithubapimock.free.beeceptor.com/repos/aggrey-aggrey/Hello-World/issues/1347");
        System.out.println("PR : " + pullrequest.getUrl());
        System.out.println("user :  "  + issue.getUser());
        System.out.println("url : "  + issue.getUrl());

    }


    @Test
    public void shouldBeAbleToCreateAGitHubIssue() throws IOException{

        Response response = IssueEndPoint.post("aggrey-aggrey", "Hello-World", issue );

        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.getBody().asString().contains("aggrey-aggrey"), equalTo(true));
        assertThat(response.jsonPath().get("user.login").toString().contentEquals(issue.getUser()),equalTo(true));
        assertThat(response.getBody().jsonPath().get("pull_request.url"),equalTo(pullrequest.getUrl()) );

    }

    @Test
    public void shouldBeAbleToGetGitHubIssueForGitHubRepoOwner() throws IOException, JSONException {

        Response response = IssueEndPoint.get("Hello-World", "aggrey-aggrey", 1347);

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.getBody().jsonPath().get("url"), equalTo(issue.getUrl()));
        assertThat(response.getBody().jsonPath().get("user.login"), equalTo(issue.getUser()));
        assertThat(response.getBody().jsonPath().get("number"),equalTo(1347));

    }

//need to add its mock
    @Test
    public void shouldBeAbleToUpdateAGitHubIssue() throws IOException{

        Response response = IssueEndPoint.patch("aggrey-aggrey", "Hello-World", 1347, issue );

        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.getBody().asString().contains("aggrey-aggrey"), equalTo(true));
        assertThat(response.jsonPath().get("user.login").toString().contentEquals(issue.getUser()),equalTo(true));
        assertThat(response.getBody().jsonPath().get("pull_request.url"),equalTo(pullrequest.getUrl()) );

    }


}
