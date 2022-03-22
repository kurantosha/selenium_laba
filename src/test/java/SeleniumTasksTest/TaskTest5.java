package SeleniumTasksTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TaskTest5 {
    private WebDriver driver;

    @Test
    public void CheckThatAlertWasOpenedWithText() {
        String expectedAlertLabel = "You clicked a button";

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");

        // Click on "Alerts, Frame & Windows" button
        WebElement windowsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']"));
        windowsButton.click();

        // Click Alerts
        WebElement alertsButton = driver.findElement(By.xpath("//div[@class='element-list collapse show']//li[@id='item-1']"));
        alertsButton.click();

        // Click on “Click me” button near “Click Button to see alert”
        WebElement clickToSeeAlertButton = driver.findElement(By.xpath("//button[@id='alertButton']"));
        clickToSeeAlertButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        String actualAlertLabel = driver.switchTo().alert().getText();

        // Click Ok button
        driver.switchTo().alert().accept();

        // Check that alert was opened with text “You clicked a button”
        Assertions.assertThat(actualAlertLabel)
                .as("We are waiting text on Alert: [" + expectedAlertLabel + "] , and the text on Alert: [" + actualAlertLabel + "]")
                .isEqualTo(expectedAlertLabel);

    }

    @AfterMethod(alwaysRun = true)
    public void quiteDriver() {
        driver.quit();
    }

}
