package utilities_self;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;

    public void openBrowser(String baseUrl) {
//setting property(key and value)
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver(); //creating instance of chrome driver
        driver.get(baseUrl);//loading webpage
        driver.manage().window().maximize(); //maximise window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));// telling selenium webdriver to wait untill everytihng loads

    }

    public void closeBrowser() {

        driver.quit();
    }
}
