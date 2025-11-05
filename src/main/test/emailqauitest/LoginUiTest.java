package emailqauitest;

import emiloqautils.TestBaseUi;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginUiTest extends TestBaseUi {

    @Test
    public void testLoginFlow() {
        System.out.println("✅ Starting Login UI test...");
        WebElement emailField = driver.findElement(AppiumBy.accessibilityId("emailInput"));
        WebElement passwordField = driver.findElement(AppiumBy.accessibilityId("passwordInput"));
        WebElement loginButton = driver.findElement(AppiumBy.accessibilityId("loginButton"));

        emailField.sendKeys("testuser@example.com");
        passwordField.sendKeys("Test@123");
        loginButton.click();

        WebElement homeScreen = driver.findElement(AppiumBy.accessibilityId("homeScreen"));
        Assert.assertTrue(homeScreen.isDisplayed(), "Login failed or home screen not visible");
    }
}
