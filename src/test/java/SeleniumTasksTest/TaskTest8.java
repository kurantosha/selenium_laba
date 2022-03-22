package SeleniumTasksTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TaskTest8 {
    private WebDriver driver;

    @Test
    public void CheckThatTextModal() {
        String expectedSmallModalTitle = "Small Modal";
        String expectedSmallModalText = "This is a small modal. It has very less content";

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");

        // Click on "Alerts, Frame & Windows" button
        WebElement windowsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']"));
        windowsButton.click();

        // close ads
        WebElement closeAds = driver.findElement(By.xpath("//a[@id='close-fixedban']"));
        closeAds.click();

        // Click Modal dialog
        WebElement modalButton = driver.findElement(By.xpath("//div[@class='element-list collapse show']//li[@id='item-4']"));
        modalButton.click();

        // Click on “Small modal” button
        WebElement smallModalButton = driver.findElement(By.xpath("//button[@id='showSmallModal']"));
        smallModalButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));

        // Check that new modal appears with title “Small Modal”
        WebElement smallModalTitleLabel = driver.findElement(By.xpath("//div[@class='modal-title h4']"));
        String actualSmallModalTitle = smallModalTitleLabel.getText();

        //Assertions
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualSmallModalTitle)
                .as("We are waiting title on Modal: [" + expectedSmallModalTitle + "] , and the title on Modal: [" + actualSmallModalTitle + "]")
                .isEqualTo(expectedSmallModalTitle);

        // With text “This is a small modal. It has very less content”
        WebElement smallModalTextLabel = driver.findElement(By.xpath("//div[@class='modal-body']"));
        String actualSmallModalText = smallModalTextLabel.getText();

        softAssertions.assertThat(actualSmallModalText)
                .as("We are waiting title on Modal: [" + expectedSmallModalText + "] , and the title on Modal: [" + actualSmallModalText + "]")
                .isEqualTo(expectedSmallModalText);

        // Close Modal window
        WebElement closeModalButton = driver.findElement(By.id("closeSmallModal"));
        closeModalButton.click();

        // Check that modal disappears
//        softAssertions.assertThat(driver.findElement(By.id("closeSmallModal")).isDisplayed())
//                .as("The Modal window was not closed")
//                .isFalse();

        softAssertions.assertAll();

    }

    @AfterMethod(alwaysRun = true)
    public void quiteDriver() {
        driver.quit();
    }

}
