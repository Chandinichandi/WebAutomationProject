package commonUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumMethods {
    private WebDriver driver;

    // Constructor to initialize WebDriver
    public SeleniumMethods(WebDriver driver) {
        this.driver = driver;
    }

    // Method to find a WebElement based on locator
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    // Method to click a WebElement
    public void clickElement(WebElement element) {
        element.click();
    }
}
