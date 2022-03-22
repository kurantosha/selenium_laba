package SeleniumTasksTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TaskTest9 {
    private WebDriver driver;

    @Test
    public void CheckThatProgressBar() {
        String expectedFiniteCountProgressBar = "100";
        String expectedInitialCountProgressBar = "0";

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");

        // Click on Widgets
        WebElement widgetsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Widgets']"));
        widgetsButton.click();

        // close ads
        WebElement closeAds = driver.findElement(By.xpath("//a[@id='close-fixedban']"));
        closeAds.click();

        WebElement progressBarButton = driver.findElement(By.xpath("//div[@class='element-list collapse show']//li[@id='item-4']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(progressBarButton));

        // Click on Progress bar
        Actions action = new Actions(driver);
        action.sendKeys(Keys.DOWN).moveToElement(progressBarButton).build().perform();
        progressBarButton.click();

        // Click Start button
        WebElement startButton = driver.findElement(By.id("startStopButton"));
        startButton.click();

        // Check that after some time progress bar filled with 100%
        WebDriverWait progressBarWail = new WebDriverWait(driver, Duration.ofSeconds(10));
        progressBarWail.until(ExpectedConditions.attributeToBe(By.xpath("//div[@role='progressbar']"), "aria-valuenow", "100"));

        WebElement finiteCountProgressBar = driver.findElement(By.xpath("//div[@role='progressbar']"));
        String actualFiniteCountProgressBar = finiteCountProgressBar.getAttribute("aria-valuenow");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualFiniteCountProgressBar)
                .as("We are waiting Finite Count in Progress Bar: [" + expectedFiniteCountProgressBar + "] , and Finite Count in Progress Bar: [" + actualFiniteCountProgressBar + "]")
                .isEqualTo(expectedFiniteCountProgressBar);

        // Click on Reset
        WebElement resetButton = driver.findElement(By.id("resetButton"));
        resetButton.click();

        // Check that progress bar value reseted to 0%
        WebElement initialCountProgressBar = driver.findElement(By.xpath("//div[@role='progressbar']"));
        String actualInitialCountProgressBar = initialCountProgressBar.getAttribute("aria-valuenow");
        softAssertions.assertThat(actualInitialCountProgressBar)
                .as("We are waiting lInitial Count in Progress Bar: [" + expectedInitialCountProgressBar + "] , and lInitial Count in Progress Bar: [" + actualInitialCountProgressBar + "]")
                .isEqualTo(expectedInitialCountProgressBar);

        softAssertions.assertAll();

    }

    @AfterMethod(alwaysRun = true)
    public void quiteDriver() {
        driver.quit();
    }

}