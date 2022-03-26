package SeleniumTasksTest;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TaskTest15 extends BaseTest {

    @Test
    public void checkThatVioletColorAppearsInField() {

        // Click on Widgets
        WebElement widgetsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Widgets']"));
        Utils.scrollToElement(driver, widgetsButton);
        widgetsButton.click();

        // Click on Auto Complete
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-1']")));
        WebElement autoCompleteButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-1']"));
        Utils.scrollToElement(driver, autoCompleteButton);
        autoCompleteButton.click();

        // Type “o” into Type multiple colors names
        WebElement typeMultipleColorsNamesInput = driver.findElement(By.id("autoCompleteMultipleInput"));
        typeMultipleColorsNamesInput.click();
        Actions actions = new Actions(driver);
        actions.sendKeys("o")
                .build().perform();
        List<WebElement> options = driver.findElements(By.xpath("//div[contains(@class,'auto-complete__option')]"));
        List<String> actualColors = new ArrayList<>();
        for (WebElement option : options) {
            String color = option.getText();
            actualColors.add(color);
        }

        // Check that “Yellow”, “Violet”, “Indigo” colors appear
        Assertions.assertThat(actualColors)
                .as("Expected Yellow, Voilet, Indigo")
                .containsExactlyInAnyOrder("Yellow", "Voilet", "Indigo");
    }
}
