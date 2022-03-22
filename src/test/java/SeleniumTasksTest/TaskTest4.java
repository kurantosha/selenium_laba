package SeleniumTasksTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TaskTest4 {
    private WebDriver driver;

    @Test
    public void CheckThatNewTabWasOpenedWithSpecifiedText() {
        String expectedTextOnPage = "This is a sample page";

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");

        // Click on "Alerts, Frame & Windows" button
        WebElement windowsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']"));
        windowsButton.click();

        // Click Browser Windows
        WebElement browserWindowsButton = driver.findElement(By.xpath("//div[@class='element-list collapse show']//li[@id='item-0']"));
        browserWindowsButton.click();

        // Click on New Tab button
        WebElement newTabButton = driver.findElement(By.xpath("//button[@id='tabButton']"));
        newTabButton.click();

        //switch to new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Check that new tab was opened with text “This is a sample page”
        WebElement textOnPageLabel = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
        String actualTextOnPage = textOnPageLabel.getText();
        Assertions.assertThat(actualTextOnPage)
                .as("We are waiting text: [" + expectedTextOnPage + "] , and the text on the page: [" + actualTextOnPage + "]")
                .isEqualTo(expectedTextOnPage);

    }

    @AfterMethod(alwaysRun = true)
    public void quiteDriver() {
        driver.quit();
    }

}