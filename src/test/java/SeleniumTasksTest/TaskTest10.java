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

public class TaskTest10 {
    private WebDriver driver;

    @Test
    public void CheckThatActiveInactiveTab() {
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

        // Click on Tabs
        WebElement tabsButton = driver.findElement(By.xpath("//div[@class='element-list collapse show']//li[@id='item-5']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(tabsButton));

        Actions action = new Actions(driver);
        action.sendKeys(Keys.DOWN).moveToElement(tabsButton).build().perform();
        tabsButton.click();

        // tabs  “What”, “Origin”, “Use”, "More"
        WebElement whatTabButton = driver.findElement(By.id("demo-tab-what"));
        WebElement originTabButton = driver.findElement(By.id("demo-tab-origin"));
        WebElement useTabButton = driver.findElement(By.id("demo-tab-use"));
        WebElement moreTabButton = driver.findElement(By.xpath("//a[@id='demo-tab-more']"));

        // Check that “What”, “Origin”, “Use” tabs active
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(whatTabButton.isEnabled())
                .as("We are waiting tab [What] is enable, but tab [What] is disable")
                .isTrue();

        softAssertions.assertThat(originTabButton.isEnabled())
                .as("We are waiting tab [Origin] is enable, but tab [Origin] is disable")
                .isTrue();

        softAssertions.assertThat(useTabButton.isEnabled())
                .as("We are waiting tab [Use] is enable, but tab [Use] is disable")
                .isTrue();

        // Check that “More” tab inactive
        softAssertions.assertThat(moreTabButton.getAttribute("aria-disabled"))
                .as("We are waiting tab [More] is disable, but tab [More] is enable")
                .isEqualTo("true");

        softAssertions.assertAll();

    }

    @AfterMethod(alwaysRun = true)
    public void quiteDriver() {
        driver.quit();
    }

}
