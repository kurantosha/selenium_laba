package SeleniumTasksTest;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

public class TaskTest5 extends BaseTest {

    @Test
    public void checkThatAlertWasOpenedWithText() {
        String expectedAlertLabel = "You clicked a button";

        // Click on "Alerts, Frame & Windows" button
        WebElement windowsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']"));
        Utils.scrollToElement(driver, windowsButton);
        windowsButton.click();

        // Click Alerts
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-1']")));
        WebElement alertsButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-1']"));
        alertsButton.click();

        // Click on “Click me” button near “Click Button to see alert”
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("alertButton")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("alertButton")));
        WebElement clickToSeeAlertButton = driver.findElement(By.id("alertButton"));
        clickToSeeAlertButton.click();

        // Click Ok button
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String actualAlertLabel = alert.getText();
        alert.accept();

        // Check that alert was opened with text “You clicked a button”
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