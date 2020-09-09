package verifyDataPack;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

public class screenshotHandle {

    static WebDriver driver;

    // Open ChromeBrowser
    @Before
    public void browser() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); // chrome browser
        driver = new ChromeDriver();
        driver.get("http://www.gmail.com");
        takeScreenshot("_1MainPage");        // Screenshot 1
        Thread.sleep(3000);
    }

        @Test
        // Test 1: Successful Gmail Login
        public void login() throws Exception {
            // email
            WebElement useremail = driver.findElement(By.id("identifierId"));
            useremail.sendKeys("example@gmail.com", Keys.ENTER);   // Enter gmail address
            takeScreenshot("_2emailPage");      // Screenshot 2
            Thread.sleep(5000);

            // password
            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("*******", Keys.ENTER);  // Enter gmail password
            takeScreenshot("_3passwordPage");          // Screenshot 3
            Thread.sleep(5000);
            takeScreenshot("_4GmailPage");             // Screenshot 4
        }

    @Test
    // Test 2: Unsuccessful Gmail Login since password is wrong
    public void captureScreenshotPasswordInvalid() throws Exception {
        // email
        WebElement useremail = driver.findElement(By.id("identifierId"));
        useremail.sendKeys("example@gmail.com", Keys.ENTER);   // Enter gmail address
        takeScreenshot("_5emailPageNextTest");                 // Screenshot 5
        Thread.sleep(5000);

        // password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("*******", Keys.ENTER);    // Enter wrong gmail password
        takeScreenshot("_6wrongPasswordEnter");      // Screenshot 6
        Thread.sleep(5000);
        takeScreenshot("_7wrongPasswordMessage");    // Screenshot 7
    }

        public static void takeScreenshot (String fileName) throws IOException {
            // 1. take Screenshot and store it as a file Format:
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // 2. now copy the screenshot to desired location using copyFile method:
            FileUtils.copyFile(file, new File("/Users/kevilkhadka/IdeaProjects/AutomationJava/src/test/screenShot/" + fileName + ".png"));     // Choose path to save all screenshots
        }

    @After
    // Close the browser
    public void Close() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }
}