package SeleniumTasksTest;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

public class TaskTest7 extends BaseTest {

    @Test
    public void checkThatTextExistInFrame() {
        String expectedTextInBigSquareLabel = "This is a sample page";

        // Click on "Alerts, Frame & Windows" button
        WebElement windowsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']"));
        Utils.scrollToElement(driver, windowsButton);
        windowsButton.click();

        // Click Frames
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-2']")));
        WebElement alertsButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-2']"));
        Utils.scrollToElement(driver, alertsButton);
        alertsButton.click();

        driver.switchTo().frame("frame1");
        WebElement textFrameLabel = driver.findElement(By.id("sampleHeading"));
        String actualTextInBigSquareLabel = textFrameLabel.getText();

        // Check that text “This is a sample page” exist in first big square
        Assertions.assertThat(actualTextInBigSquareLabel)
                .as("We are waiting text in Frame: [" + expectedTextInBigSquareLabel + "] , and the text in Frame: [" + actualTextInBigSquareLabel + "]")
                .isEqualTo(expectedTextInBigSquareLabel);

    }
}