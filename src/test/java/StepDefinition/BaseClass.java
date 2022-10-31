package StepDefinition;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    WebDriver driver;
    WebDriverWait wait;

    public void setup() throws IOException {
        FileReader reader = new FileReader("src\\test\\java\\TestData\\Data.properties");
        Properties testData = new Properties();
        testData.load(reader);
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.navigate().to(testData.getProperty("url"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void closeBrowser() {
        driver.quit();
    }
}