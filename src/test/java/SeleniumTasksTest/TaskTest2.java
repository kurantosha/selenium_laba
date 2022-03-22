package SeleniumTasksTest;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

public class TaskTest2 extends BaseTest {

    @Test
    public void checkThatAppearsMessageWhenSelectedWordFile() {
        String expectedTextResult = "You have selected :wordFile";

        // Click on "Elements button"
        WebElement elementsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Elements']"));
        Utils.scrollToElement(driver, elementsButton);
        elementsButton.click();

        // Click on "Check Box"
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-1']")));
        WebElement checkBoxButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-1']"));
        checkBoxButton.click();

        // Uncollapse "Home"
        WebElement uncollapseHomeButton = driver.findElement(By.xpath("//label[@for='tree-node-home']/preceding-sibling::button"));
        uncollapseHomeButton.click();

        // Uncollapse "Downloads"
        WebElement uncollapseDownloadsButton = driver.findElement(By.xpath("//label[@for='tree-node-downloads']/preceding-sibling::button"));
        uncollapseDownloadsButton.click();

        // Select Word File.doc
        WebElement wordFileCheckBox = driver.findElement(By.xpath("//label[@for='tree-node-wordFile']/span[@class='rct-checkbox']"));
        wordFileCheckBox.click();

        WebElement selectedMessage = driver.findElement(By.xpath("//div[@id='result']/span[@class='text-success']"));
        String actualTextResult = "You have selected :" + selectedMessage.getText();

        // Check that “You have selected :wordFile” appears
        Assertions.assertThat(actualTextResult)
                .as("should receive a message: " + expectedTextResult)
                .isEqualTo(expectedTextResult);

    }
}