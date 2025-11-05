package comemiloqatests;

import com.emilo.qa.utils.TestContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    @Test(priority = 2, dependsOnMethods = "comemiloqatests.RegistrationTest.testUserRegistration")
    public void testUserLogin() {
        RestAssured.baseURI = "https://emilo-live-stream-back-1.onrender.com";
        System.out.println("Base URI set to: " + RestAssured.baseURI);

        String email = TestContext.email;
        String password = TestContext.password;

        Assert.assertNotNull(email, "Email missing — please run RegistrationTest first.");
        Assert.assertNotNull(password, "Password missing — please run RegistrationTest first.");

        String requestBody = "{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }";

        System.out.println("Logging in with: " + email);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/auth/login")
                .then()
                .extract().response();

        System.out.println("Response: " + response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), 200, "Login failed!");

        //  Save auth token globally
        TestContext.authToken = response.jsonPath().getString("token");
        System.out.println(" Auth Token stored: " + TestContext.authToken);
    }
}
