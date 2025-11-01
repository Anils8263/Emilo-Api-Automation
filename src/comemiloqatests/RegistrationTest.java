package comemiloqatests;

import com.emilo.qa.utils.TestContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest {

    @Test(priority = 1)
    public void testUserRegistration() {
        RestAssured.baseURI = "https://emilo-live-stream-back-1.onrender.com";
        System.out.println("Base URI set to: " + RestAssured.baseURI);

        // Generate a unique user for each test run
        String email = "testuser_" + System.currentTimeMillis() + "@example.com";
        String password = "Test@123";

        // Request body
        String requestBody = "{"
                + "\"name\": \"Test User\","
                + "\"email\": \"" + email + "\","
                + "\"password\": \"" + password + "\""
                + "}";

        System.out.println("Registering user: " + email);

        // Send registration request
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/auth/register")
                .then()
                .extract().response();

        // Print and verify
        System.out.println("Response: " + response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), 201, "Registration failed!");

        //  Save values for next tests
        TestContext.email = email;
        TestContext.password = password;

        System.out.println(" Registration successful for: " + email);
        System.out.println(" Saved to TestContext for login: " + TestContext.email);
    }
}
