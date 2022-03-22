package SeleniumTasksTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TaskTest2 {
    private WebDriver driver;

    @Test
    public void CheckThatAppearsMessageWhenSelectedWordFile() {
        String expectedTextResult = "You have selected :wordFile";

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");

        // Click on "Elements button"
        WebElement elementsButton = driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Elements']"));
        elementsButton.click();

        // Click on "Check Box"
        WebElement checkBoxButton = driver.findElement(By.xpath("//div[@class='element-list collapse show']//li[@id='item-1']"));
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

//        Assertions.assertThat(wordFileCheckBox.isSelected())
//                .as("After click on WordFile.doc checkbox it should be selected")
//                .isTrue();

        WebElement selectedMessage = driver.findElement(By.xpath("//div[@id='result']/span[@class='text-success']"));
        String actualTextResult = "You have selected :" + selectedMessage.getText();


        // Check that “You have selected :wordFile” appears
        Assertions.assertThat(actualTextResult)
                .as("should receive a message: " + expectedTextResult)
                .isEqualTo(expectedTextResult);

    }

    @AfterMethod(alwaysRun = true)
    public void quiteDriver() {
        driver.quit();
    }

}
