package SeleniumTasksTest;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

public class TaskTest8 extends BaseTest {

    @Test
    public void checkThatTextModal() {
        String expectedSmallModalTitle = "Small Modal";
        String expectedSmallModalText = "This is a small modal. It has very less content";

        // Click on "Alerts, Frame & Windows" button
        WebElement windowsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']"));
        Utils.scrollToElement(driver, windowsButton);
        windowsButton.click();

        // Click Modal dialog
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-4']")));
        WebElement modalButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-4']"));
        Utils.scrollToElement(driver, modalButton);
        modalButton.click();

        // Click on “Small modal” button
        WebElement smallModalButton = driver.findElement(By.xpath("//button[@id='showSmallModal']"));
        Utils.scrollToElement(driver, smallModalButton);
        smallModalButton.click();

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
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("example-modal-sizes-title-sm")));

        softAssertions.assertAll();
    }
}