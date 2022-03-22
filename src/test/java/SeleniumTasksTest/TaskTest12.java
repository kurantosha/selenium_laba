package SeleniumTasksTest;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

public class TaskTest12 extends BaseTest {

    @Test(invocationCount = 10)
    public void checkThatSelectedValue() {
        String expectedSelectValue = "Ms.";

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

        // From menu Select One select Ms.
        WebElement selectOneList = driver.findElement(By.id("selectOne"));
        selectOneList.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selectOne")));
        WebElement valueLabel = driver.findElement(By.xpath("//*[contains(@class,'menu')]//div[@id='react-select-3-option-0-3']"));
        valueLabel.click();

        // Check that Ms. appears as select value
        WebElement valueSelectBox = driver.findElement(By.xpath("//*[contains(@class,'singleValue')]"));
        String actualSelectValue = valueSelectBox.getText();

        Assertions.assertThat(actualSelectValue)
                .as("We can select value - " + expectedSelectValue + ", but select - " + actualSelectValue)
                .isEqualTo(expectedSelectValue);
    }
}