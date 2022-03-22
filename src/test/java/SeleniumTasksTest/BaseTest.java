package SeleniumTasksTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        closeAds();
    }

    public void closeAds() {
        WebElement adArrow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("close-fixedban")));
        adArrow.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("close-fixedban")));
    }

    @AfterMethod(alwaysRun = true)
    public void quiteDriver() {
        driver.quit();
    }

}
