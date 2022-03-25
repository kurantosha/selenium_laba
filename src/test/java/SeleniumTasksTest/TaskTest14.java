package SeleniumTasksTest;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TaskTest14 extends BaseTest {

    @Test
    public void checkThatFirstNameFieldBorderColorAndDateOfBirth() {

        String expectedColorBorderFistNameInput = "rgb(220, 53, 69)";
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy", new Locale("en")));

        // Click on Forms
        WebElement formsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Forms']"));
        Utils.scrollToElement(driver, formsButton);
        formsButton.click();

        // Click on Practice form
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-0']")));
        WebElement practiceFormButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-0']"));
        Utils.scrollToElement(driver, practiceFormButton);
        practiceFormButton.click();

        // Do not fill any field and click on Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit")));
        Utils.scrollToElement(driver, submitButton);
        submitButton.click();

        // Check that First name field highlighted in red
        WebElement fistNameInput = driver.findElement(By.id("firstName"));
        String actualColorBorderFistNameInput = fistNameInput.getCssValue("border-color");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualColorBorderFistNameInput)
                .as("We are waiting border color in FistName input field: " + expectedColorBorderFistNameInput + "and received color: " + actualColorBorderFistNameInput)
                .isEqualTo(expectedColorBorderFistNameInput);

        // Check that Date of Birth filled with today date
        WebElement dateOfBirthInput = driver.findElement(By.id("dateOfBirthInput"));
        String dateOfBirth = dateOfBirthInput.getAttribute("value");

        softAssertions.assertThat(dateOfBirth)
                .as("We are waiting date: " + dateOfBirth + "and today's date: " + todayDate)
                .isEqualTo(todayDate);

        softAssertions.assertAll();
    }
}