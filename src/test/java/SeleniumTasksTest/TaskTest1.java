package SeleniumTasksTest;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

public class TaskTest1 extends BaseTest {

    @Test
    public void checkThatFilledDataTheSameAsAppearsBelowTest() {
        String expectedUserName = "Anton Kurganov";
        String expectedUserEmail = "mail@gmail.com";
        String expectedCurrentAddress = "vul.Soborna 1, Vinnitsa";
        String expectedPermanentAddress = "vul.Soborna 2, Vinnitsa";

        // Click on Elements button
        WebElement elementsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Elements']"));
        Utils.scrollToElement(driver, elementsButton);
        elementsButton.click();

        // Click on Text Box
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-0']")));
        WebElement textBoxButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-0']"));
        textBoxButton.click();

        // Input form
        WebElement userNameInput = driver.findElement(By.xpath("//input[@id='userName']"));
        userNameInput.clear();
        userNameInput.sendKeys(expectedUserName);

        WebElement userEmailInput = driver.findElement(By.xpath("//input[@id='userEmail']"));
        userEmailInput.clear();
        userEmailInput.sendKeys(expectedUserEmail);

        WebElement currentAddressInput = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        currentAddressInput.clear();
        currentAddressInput.sendKeys(expectedCurrentAddress);

        WebElement permanentAddressInput = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        permanentAddressInput.clear();
        permanentAddressInput.sendKeys(expectedPermanentAddress);

        // Click Submit button
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));
        Utils.scrollToElement(driver, submitButton);
        submitButton.click();

        // Output data
        WebElement outputNameLabel = driver.findElement(By.xpath("//p[@id='name']"));
        String actualUserName = outputNameLabel.getText().replaceAll("Name:", "");

        WebElement outputEmailLabel = driver.findElement(By.xpath("//p[@id='email']"));
        String actualUserEmail = outputEmailLabel.getText().replaceAll("Email:", "");

        WebElement outputCurrentAddressLabel = driver.findElement(By.xpath("//p[@id='currentAddress']"));
        String actualCurrentAddress = outputCurrentAddressLabel.getText().replaceAll("Current Address :", "");

        WebElement outputPermanentAddressLabel = driver.findElement(By.xpath("//p[@id='permanentAddress']"));
        String actualPermanentAddress = outputPermanentAddressLabel.getText().replaceAll("Permananet Address :", "");

        // Assertions
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualUserName)
                .as("the input user name: " + expectedUserName + " does not correspond to the output user name: " + actualUserName)
                .isEqualTo(expectedUserName);

        softAssertions.assertThat(actualUserEmail)
                .as("the input user email: " + expectedUserEmail + " does not correspond to the output user email: " + actualUserEmail)
                .isEqualTo(expectedUserEmail);

        softAssertions.assertThat(actualCurrentAddress)
                .as("the input user Current Address): " + expectedCurrentAddress + " does not correspond to the output user Current Address): " + actualCurrentAddress)
                .isEqualTo(expectedCurrentAddress);

        softAssertions.assertThat(actualPermanentAddress)
                .as("the input user Permanent Address): " + expectedPermanentAddress + " does not correspond to the output user Permanent Address): " + actualPermanentAddress)
                .isEqualTo(expectedPermanentAddress);

        softAssertions.assertAll();

    }
}