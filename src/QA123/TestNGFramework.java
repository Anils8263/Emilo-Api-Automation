package QA123;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNGFramework {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/login/");
        driver.manage().window().maximize();
    }

    @Test
    public void verifyLogin() {
        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("anilshindepatil8263@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("@Anils8263");

        //  Incorrect: driver.findElement(By.name"Loginn")).click();
        //  Correct:
        driver.findElement(By.name("login")).click();

        // Get and verify title (example check)
        String expected = "Facebook";
        String actual = driver.getTitle();

        Assert.assertEquals(actual, expected, "Login failed!");
    }

    @AfterMethod
    public void tearDown() {
        //driver.quit();
    }
}
