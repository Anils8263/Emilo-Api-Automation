package comemiloqatests;

import com.emilo.qa.utils.TestContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddComment {

    @Test(priority = 6, dependsOnMethods = "comemiloqatests.CreatePostTest.testCreatePost")
    public void testAddComment() {
        Assert.assertNotNull(TestContext.authToken, "Auth token missing!");
        Assert.assertNotNull(TestContext.postId, "Post ID missing!");

        RestAssured.baseURI = "https://emilo-live-stream-back-1.onrender.com";

        String requestBody = "{ \"text\": \"Automated comment from test\" }";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + TestContext.authToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/comments/" + TestContext.postId)
                .then()
                .extract().response();

        System.out.println("Add Comment Response: " + response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), 201);
    }
}
