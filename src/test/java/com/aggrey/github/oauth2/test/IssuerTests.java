package com.aggrey.github.oauth2.test;

import com.aggrey.github.oauth2.api.applicationApi.OwnerApi;

import com.aggrey.github.oauth2.api.applicationApi.IssueEndPoint;
import com.aggrey.github.oauth2.pojo.Issue;
import com.aggrey.github.oauth2.pojo.Owner;
import com.aggrey.github.oauth2.pojo.Repository;
import com.aggrey.github.oauth2.pojo.User;
import com.aggrey.github.oauth2.utils.ConfigLoader;
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

    public IssuerTests() throws IOException {
    }


    @BeforeClass
    public void beforeClass(){
        user = new User("aggrey-aggrey", 123,"https://mygithubapimock.free.beeceptor.com/users/aggrey-aggrey" );
        pullrequest = new PullRequest("https://mygithubapimock.free.beeceptor.com/repos/aggrey-aggrey/Hello-World/pulls/1347");
        issue =  new Issue("aggrey-aggrey",  pullrequest ,"https://mygithubapimock.free.beeceptor.com/repos/aggrey-aggrey/Hello-World/issues/1347","a bug");
        System.out.println("PR : " + pullrequest.getUrl());
        System.out.println("user :  "  + issue.getUser());
        System.out.println("url : "  + issue.getUrl());
        System.out.println("title : "  + issue.getTitle());

    }


    @Test
    public void shouldBeAbleTo_CreateAGitHubIssue() throws IOException{

        Response response = IssueEndPoint.post("aggrey-aggrey", "Hello-World", issue );

        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.getBody().asString().contains("aggrey-aggrey"), equalTo(true));
        assertThat(response.jsonPath().get("user.login").toString().contentEquals(issue.getUser()),equalTo(true));
        assertThat(response.getBody().jsonPath().get("pull_request.url"),equalTo(pullrequest.getUrl()) );

    }

    @Test
    public void shouldNot_BeAbleTo_CreateAGitHubIssue_with_missing_issueTitle() throws IOException{
        Issue issue =  new Issue("aggrey-aggrey",  pullrequest ,"https://mygithubapimock.free.beeceptor.com/repos/aggrey-aggrey/repos/aggrey-aggrey/Hello-World-Bad-Request/issues","");

        Response response = IssueEndPoint.post("aggrey", "", issue );
        assertThat(response.statusCode(), equalTo(422));
        assertThat(response.getBody().jsonPath().get("message"), equalTo("M422 Unprocessable Entity"));

    }

    @Test
    public void shouldBeAble_ToGet_GitHubIssue_ForGitHubRepoOwner() throws IOException, JSONException {

        Response response = IssueEndPoint.get("Hello-World", "aggrey-aggrey", 1347);

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.getBody().jsonPath().get("url"), equalTo(issue.getUrl()));
        assertThat(response.getBody().jsonPath().get("user.login"), equalTo(issue.getUser()));
        assertThat(response.getBody().jsonPath().get("number"),equalTo(1347));

    }

    @Test
    public void shouldNot_BeAbleToGet_GitHubIssue_ForGitHubRepoOwner_With_Invalid_IssueId() throws IOException, JSONException {

        Response response = IssueEndPoint.get("Hello-World", "aggrey-aggrey", 1347);

        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.getBody().jsonPath().get("message"), equalTo("404 Not Found"));
        assertThat(response.getBody().jsonPath().get("number"),equalTo(1347));

    }

    @Test
    public void shouldBeAbleToUpdate_AGitHubIssue() throws IOException{

        String updatedIssueTitle  = issue.getTitle() + "-UPDATED";

        Response response = IssueEndPoint.patch("aggrey-aggrey", "Hello-World", 1347, issue );

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.getBody().asString().contains("aggrey-aggrey"), equalTo(true));
        assertThat(response.jsonPath().get("user.login").toString().contentEquals(issue.getUser()),equalTo(true));
        assertThat(response.getBody().jsonPath().get("pull_request.url"),equalTo(pullrequest.getUrl()));
        assertThat(response.getBody().jsonPath().get("title"), equalTo(updatedIssueTitle));

    }

    @Test
    public void shouldReturnHeaders_In_ResponseBody() throws IOException{

        Response response = IssueEndPoint.post("aggrey-aggrey", "Hello-World", issue );

        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.getHeader("Authorization"), equalTo(ConfigLoader.getInstance().getAccessToken()));
        assertThat(response.getHeader("Content-Type"), equalTo("application/json"));

    }



}
