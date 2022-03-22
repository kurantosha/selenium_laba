package SeleniumTasksTest;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.Utils;

import java.time.Duration;

public class TaskTest6 extends BaseTest {

    @Test(invocationCount = 10)
    public void checkThatAlertWasOpenedWithTextAfter5Seconds() {
        String expectedAlertLabel = "This alert appeared after 5 seconds";

        // Click on "Alerts, Frame & Windows" button
        WebElement windowsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']"));
        Utils.scrollToElement(driver, windowsButton);
        windowsButton.click();

        // Click Alerts
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-1']")));
        WebElement alertsButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-1']"));
        alertsButton.click();

        // Click on “Click me” button near “On button click, alert will appear after 5 seconds”
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("timerAlertButton")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("timerAlertButton")));
        WebElement alertButton = driver.findElement(By.id("timerAlertButton"));
        alertButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        // Get Text and Click Ok button
        Alert alert = driver.switchTo().alert();
        String actualAlertLabel = alert.getText();
        alert.accept();

        // Check that alert was opened with text “This alert appeared after 5 seconds”
        Assertions.assertThat(actualAlertLabel)
                .as("We are waiting text on Alert: [" + expectedAlertLabel + "] , and the text on Alert: [" + actualAlertLabel + "]")
                .isEqualTo(expectedAlertLabel);

        // Check that alert disapear
        boolean isAlertStillPresent;
        try {
            driver.switchTo().alert();
            isAlertStillPresent = true;
        } catch (NoAlertPresentException e) {
            isAlertStillPresent = false;
        }

        Assertions.assertThat(isAlertStillPresent)
                .as("Alert open? - " + isAlertStillPresent)
                .isEqualTo(false);

    }
}