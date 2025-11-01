package comemiloqatests;

import com.emilo.qa.utils.TestContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreatePostTest {

    @Test(priority = 3, dependsOnMethods = "comemiloqatests.LoginTest.testUserLogin")
    public void testCreatePost() {
        RestAssured.baseURI = "https://emilo-live-stream-back-1.onrender.com";
        System.out.println("Base URI set to: " + RestAssured.baseURI);

        // Check token
        Assert.assertNotNull(TestContext.authToken, "Auth token is missing! Please run LoginTest first.");

        String requestBody = """
        {
          "content": "Automated test post from API",
          "mediaUrl": "https://example.com/sample.jpg",
          "mediaType": "image"
        }
        """;

        System.out.println("Creating post...");
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + TestContext.authToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/posts/create")
                .then()
                .extract().response();

        System.out.println("Response: " + response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), 201, "Post creation failed!");

        //  Save postId
        String postId = response.jsonPath().getString("post._id");
        Assert.assertNotNull(postId, "postId is null — post creation response invalid!");
        TestContext.postId = postId;

        System.out.println(" Post created successfully with ID: " + postId);
    }
}
