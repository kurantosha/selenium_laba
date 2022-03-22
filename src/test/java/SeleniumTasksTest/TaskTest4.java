package SeleniumTasksTest;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

import java.util.ArrayList;

public class TaskTest4 extends BaseTest {

    @Test
    public void checkThatNewTabWasOpenedWithSpecifiedText() {
        String expectedTextOnPage = "This is a sample page";

        // Click on "Alerts, Frame & Windows" button
        WebElement windowsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']"));
        Utils.scrollToElement(driver, windowsButton);
        windowsButton.click();

        // Click Browser Windows
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-0']")));
        WebElement browserWindowsButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-0']"));
        browserWindowsButton.click();

        // Click on New Tab button
        WebElement newTabButton = driver.findElement(By.xpath("//button[@id='tabButton']"));
        newTabButton.click();

        //switch to new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Check that new tab was opened with text “This is a sample page”
        WebElement textOnPageLabel = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
        String actualTextOnPage = textOnPageLabel.getText();
        Assertions.assertThat(actualTextOnPage)
                .as("We are waiting text: [" + expectedTextOnPage + "] , and the text on the page: [" + actualTextOnPage + "]")
                .isEqualTo(expectedTextOnPage);

    }
}