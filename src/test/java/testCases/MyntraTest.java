package testCases;

import commonUtilities.SeleniumMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class MyntraTest {
    public static void main(String[] args) throws InterruptedException {
        // 1. Set up Chrome options and launch the browser
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.myntra.com/");

        // 2. Create object of SeleniumMethods and pass driver
        SeleniumMethods util = new SeleniumMethods(driver);

        // 3. Hover on "Men" section
        WebElement menSection = util.findElement(By.xpath("//a[@data-group='men' and @class='desktop-main']"));
        util.clickElement(menSection); // Click "Men" instead of hover (simplified)

        // 4. Click on "Jeans"
        WebElement jeansLink = util.findElement(By.linkText("Jeans"));
        util.clickElement(jeansLink);

        // 5. Filter by "Levis"
        WebElement levisFilter = util.findElement(By.xpath("//label[.//input[@value='Levis']]"));
        util.clickElement(levisFilter);

        // 6. Wait for products to load and find product elements
        Thread.sleep(3000); // Wait for the page to load
        List<WebElement> products = driver.findElements(By.className("product-base"));

        // 7. Click on the first two products, select size, and add to cart
        for (int i = 0; i < 2; i++) {
            // Click product
            products.get(i).click();
            Thread.sleep(2000);

            // Switch to new tab
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            // Select size
            WebElement sizeButton = util.findElement(By.xpath("//div[@class='size-buttons-buttonContainer']/button[not(@disabled)]"));
            util.clickElement(sizeButton);

            // Add to Bag
            WebElement addToBagButton = util.findElement(By.xpath("//div[contains(text(),'ADD TO BAG')]"));
            util.clickElement(addToBagButton);

            System.out.println("Product " + (i + 1) + " added to the bag.");

            // Close current tab and switch back to the original window
            driver.close();
            driver.switchTo().window(tabs.get(0));

            // Refresh product list
            products = driver.findElements(By.className("product-base"));
        }

        System.out.println("✅ Automation completed successfully!");
        driver.quit();
    }
}
