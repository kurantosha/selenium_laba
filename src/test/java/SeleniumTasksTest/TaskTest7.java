package SeleniumTasksTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TaskTest7 {

    private WebDriver driver;

    @Test
    public void CheckThatTextExistInFrame() {
        String expectedTextInBigSquareLabel = "This is a sample page";

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");

        // Click on "Alerts, Frame & Windows" button
        WebElement windowsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']"));
        windowsButton.click();

        // Click Frames
        WebElement alertsButton = driver.findElement(By.xpath("//div[@class='element-list collapse show']//li[@id='item-2']"));
        alertsButton.click();

        driver.switchTo().frame("frame1");
        WebElement textFrameLabel = driver.findElement(By.id("sampleHeading"));
        String actualTextInBigSquareLabel = textFrameLabel.getText();

        // Check that text “This is a sample page” exist in first big square
        Assertions.assertThat(actualTextInBigSquareLabel)
                .as("We are waiting text in Frame: [" + expectedTextInBigSquareLabel + "] , and the text in Frame: [" + actualTextInBigSquareLabel + "]")
                .isEqualTo(expectedTextInBigSquareLabel);

    }

    @AfterMethod(alwaysRun = true)
    public void quiteDriver() {
        driver.quit();
    }
}