package QA123;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
// Optional if you want automatic ChromeDriver management
//import io.github.bonigarcia.wdm.WebDriverManager;

public class Automate1 {
    public static void main(String[] args) throws InterruptedException {

        // If using WebDriverManager
        //WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Otherwise, set path manually:
        // System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");

       // WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.instagram.com/accounts/login/");

        Thread.sleep(5000); // wait for login form

        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("anilshindepatil8263@gmail.com");

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("@Anils8263");

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        Thread.sleep(5000); // wait to see result
        System.out.println("Page title after login: " + driver.getTitle());

       // driver.quit();
    }
}

