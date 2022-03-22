package SeleniumTasksTest;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import utils.Utils;

public class TaskTest11 extends BaseTest {

    @Test
    public void checkThatSelectedWhite() {
        String expectedColor = "White";

        // Click on Widgets
        WebElement widgetsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Widgets']"));
        Utils.scrollToElement(driver, widgetsButton);
        widgetsButton.click();

        // Click on Select Menu
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-8']")));
        WebElement selectMenuButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-8']"));
        Utils.scrollToElement(driver, selectMenuButton);
        selectMenuButton.click();

        // Select “White” from Old Style Select Menu
        WebElement selectMenuElements = driver.findElement(By.id("oldSelectMenu"));
        Select select = new Select(selectMenuElements);
        select.selectByVisibleText("White");

        String actualColor = select.getFirstSelectedOption().getText();

        // Check that “White” was selected
        Assertions.assertThat(actualColor)
                .as("We are waiting selected color: [" + expectedColor + "], and selected color: [" + actualColor)
                .isEqualTo(expectedColor);
    }
}