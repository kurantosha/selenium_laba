package SeleniumTasksTest;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

public class TaskTest3 extends BaseTest {

    @Test
    public void checkThatAppearsMessageWhenSelectedWordFile() {
        String expectedClickMeMessage = "You have done a dynamic click";
        String expectedRightClickMeMessage = "You have done a right click";
        String expectedDoubleClickMeMessage = "You have done a double click";

        Actions actions = new Actions(driver);

        // Click on "Elements button"
        WebElement elementsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Elements']"));
        Utils.scrollToElement(driver, elementsButton);
        elementsButton.click();

        // Click "Buttons"
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-4']")));
        WebElement buttonsButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-4']"));
        buttonsButton.click();

        // Click on “Click Me” button
        WebElement clickMeButton = driver.findElement(By.xpath("//button[text()='Click Me']"));
        clickMeButton.click();
        // Message on “Click Me” button
        WebElement clickMeLabel = driver.findElement(By.id("dynamicClickMessage"));
        String actualClickMeMessage = clickMeLabel.getText();

        // Click with context on “Right click me button”
        WebElement rightClickMeButton = driver.findElement(By.xpath("//button[@id='rightClickBtn']"));
        actions.contextClick(rightClickMeButton)
                .build().perform();
        // Message on “Right click me button” button
        WebElement rightClickMeLabel = driver.findElement(By.id("rightClickMessage"));
        String actualRightClickMeMessage = rightClickMeLabel.getText();

        // Double-click on “Double Click Me” button
        WebElement doubleClickMeButton = driver.findElement(By.xpath("//button[@id='doubleClickBtn']"));
        actions.doubleClick(doubleClickMeButton)
                .build().perform();
        // Message on “Double Click Me” button
        WebElement doubleClickMeLabel = driver.findElement(By.id("doubleClickMessage"));
        String actualDoubleClickMeMessage = doubleClickMeLabel.getText();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualClickMeMessage)
                .as("After click on button [Click Me] we are waiting for a message: " + expectedClickMeMessage)
                .isEqualTo(expectedClickMeMessage);

        softAssertions.assertThat(actualRightClickMeMessage)
                .as("After click on button [Right click me button] we are waiting for a message: " + expectedRightClickMeMessage)
                .isEqualTo(expectedRightClickMeMessage);

        softAssertions.assertThat(actualDoubleClickMeMessage)
                .as("After click on button [Right click me button] we are waiting for a message: " + expectedDoubleClickMeMessage)
                .isEqualTo(expectedDoubleClickMeMessage);

        softAssertions.assertAll();

    }
}