package SeleniumTasksTest;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Utils;

public class TaskTest10 extends BaseTest {

    @Test(invocationCount = 5)
    public void checkThatActiveInactiveTab() {

        // Click on Widgets
        WebElement widgetsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Widgets']"));
        Utils.scrollToElement(driver, widgetsButton);
        widgetsButton.click();

        // Click on Tabs
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'show')]//li")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'show')]//li[@id='item-5']")));
        WebElement tabsButton = driver.findElement(By.xpath("//div[contains(@class, 'show')]//li[@id='item-5']"));
        Utils.scrollToElement(driver, tabsButton);
        tabsButton.click();

        // tabs  “What”, “Origin”, “Use”, "More"
        WebElement whatTabButton = driver.findElement(By.id("demo-tab-what"));
        WebElement originTabButton = driver.findElement(By.id("demo-tab-origin"));
        WebElement useTabButton = driver.findElement(By.id("demo-tab-use"));
        WebElement moreTabButton = driver.findElement(By.xpath("//a[@id='demo-tab-more']"));

        // Check that “What”, “Origin”, “Use” tabs active
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(whatTabButton.getAttribute("class").contains("disabled"))
                .as("We are waiting tab [What] is enable, but tab [What] is disable")
                .isFalse();

        softAssertions.assertThat(originTabButton.getAttribute("class").contains("disabled"))
                .as("We are waiting tab [Origin] is enable, but tab [Origin] is disable")
                .isFalse();

        softAssertions.assertThat(useTabButton.getAttribute("class").contains("disabled"))
                .as("We are waiting tab [Use] is enable, but tab [Use] is disable")
                .isFalse();

        // Check that “More” tab inactive
        softAssertions.assertThat(moreTabButton.getAttribute("class").contains("disabled"))
                .as("We are waiting tab [More] is disable, but tab [More] is enable")
                .isTrue();

        softAssertions.assertAll();
    }
}