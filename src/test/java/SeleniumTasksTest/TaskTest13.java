package SeleniumTasksTest;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

public class TaskTest13 extends BaseTest  {

    @Test
    public void checkThatSelectedDynamicProperties(){
        String activeColor = "rgba(0, 123, 255, 1)";
        String deActiveColor = "rgba(255, 255, 255, 1)";

        // Click on Interactions
        WebElement interactionsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Interactions']"));
        Utils.scrollToElement(driver, interactionsButton);
        interactionsButton.click();

        // Click on Selectable
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-1']")));
        WebElement selectableButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-1']"));
        Utils.scrollToElement(driver, selectableButton);
        selectableButton.click();

        // Select “Cras justo odio”
        WebElement value1 = driver.findElement(By.xpath("//li[text()='Cras justo odio']"));
        value1.click();

        // Select “Morbi leo risus”
        WebElement value3 = driver.findElement(By.xpath("//li[text()='Morbi leo risus']"));
        value3.click();

        // Check that “Cras justo odio” and “Morbi leo risus” was selected
        String colorValue1 = value1.getCssValue("background-color");
        String colorValue3 = value3.getCssValue("background-color");
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(colorValue1)
                .as("We are waiting selected color code value1: " + activeColor + ", and selected color code " + colorValue1)
                .isEqualTo(activeColor);

        softAssertions.assertThat(colorValue3)
                .as("We are waiting selected color code value3: " + activeColor + ", and selected color code " + colorValue1)
                .isEqualTo(activeColor);

        // Check that “Dapibus ac facilisis in” and “Porta ac consectetur ac” not selected
        WebElement value2 = driver.findElement(By.xpath("//li[text()='Dapibus ac facilisis in']"));
        String colorValue2 = value2.getCssValue("background-color");

        WebElement value4 = driver.findElement(By.xpath("//li[text()='Porta ac consectetur ac']"));
        String colorValue4 = value4.getCssValue("background-color");

        softAssertions.assertThat(colorValue2)
                .as("We are waiting selected color code value2: " + activeColor + ", and selected color code " + colorValue1)
                .isEqualTo(deActiveColor);

        softAssertions.assertThat(colorValue4)
                .as("We are waiting selected color code value4: " + activeColor + ", and selected color code " + colorValue1)
                .isEqualTo(deActiveColor);

        softAssertions.assertAll();
    }
}