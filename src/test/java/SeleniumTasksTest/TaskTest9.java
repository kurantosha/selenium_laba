package SeleniumTasksTest;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

public class TaskTest9 extends BaseTest {

    @Test
    public void checkThatProgressBar() {
        String expectedFiniteCountProgressBar = "100";
        String expectedInitialCountProgressBar = "0";

        // Click on Widgets
        WebElement widgetsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Widgets']"));
        Utils.scrollToElement(driver, widgetsButton);
        widgetsButton.click();

        // Click on Progress bar
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-4']")));
        WebElement progressBarButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-4']"));
        Utils.scrollToElement(driver, progressBarButton);
        progressBarButton.click();

        // Click Start button
        WebElement startButton = driver.findElement(By.id("startStopButton"));
        startButton.click();

        // Check that after some time progress bar filled with 100%
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[@role='progressbar']"), "aria-valuenow", "100"));

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
}