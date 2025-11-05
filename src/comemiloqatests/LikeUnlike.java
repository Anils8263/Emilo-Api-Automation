package comemiloqatests;

import com.emilo.qa.utils.TestContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LikeUnlike {

    @Test(priority = 4, dependsOnMethods = "comemiloqatests.CreatePostTest.testCreatePost")
    public void testLikePost() {
        Assert.assertNotNull(TestContext.authToken, "Auth token missing!");
        Assert.assertNotNull(TestContext.postId, "Post ID missing!");

        RestAssured.baseURI = "https://emilo-live-stream-back-1.onrender.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + TestContext.authToken)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/posts/like/" + TestContext.postId)
                .then()
                .extract().response();

        System.out.println("Like Response: " + response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 5, dependsOnMethods = "testLikePost")
    public void testUnlikePost() {
        RestAssured.baseURI = "https://emilo-live-stream-back-1.onrender.com";
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + TestContext.authToken)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/posts/unlike/" + TestContext.postId)
                .then()
                .extract().response();

        System.out.println("Unlike Response: " + response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), 200);
    }
}
