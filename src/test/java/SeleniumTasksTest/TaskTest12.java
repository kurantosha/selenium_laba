package SeleniumTasksTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
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

public class TaskTest12 {
    private WebDriver driver;

    @Test
    public void CheckThatSelectedValue() {
        String expectedSelectValue = "Ms.";

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

        // Click on Select Menu
        WebElement selectMenuButton = driver.findElement(By.xpath("//div[@class='element-list collapse show']//li[@id='item-8']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(selectMenuButton));

        Actions action = new Actions(driver);
        action.sendKeys(Keys.DOWN).moveToElement(selectMenuButton).build().perform();
        selectMenuButton.click();

        // From menu Select One select Ms.
        WebElement selectOneList = driver.findElement(By.id("selectOne"));
        selectOneList.click();

        WebDriverWait waitSelect = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitSelect.until(ExpectedConditions.presenceOfElementLocated(By.id("selectOne")));

        Actions actionSelect = new Actions(driver);
        actionSelect.sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).build().perform();

        // Check that Ms. appears as select value
        WebElement valueSelectBox = driver.findElement(By.xpath("//*[contains(@class,'singleValue')]"));
        String actualSelectValue = valueSelectBox.getText();

        Assertions.assertThat(actualSelectValue)
                .as("We can select value - " + expectedSelectValue + ", but select - " + actualSelectValue)
                .isEqualTo(expectedSelectValue);

    }

    @AfterMethod(alwaysRun = true)
    public void quiteDriver() {
        driver.quit();
    }

}