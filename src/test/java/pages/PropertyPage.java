package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PropertyPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public PropertyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    // Locators
    By titleInput = By.name("title");
    By priceInput = By.name("price");
    By locationInput = By.name("location");
    By descInput = By.name("description");
    By selectImageBtn = By.xpath("//button[contains(.,'Select Image')]");
    By fileInput = By.xpath("//input[@type='file']");
    By submitButton = By.cssSelector("button.submit-btn");



    public void enterTitle(String title) {
        driver.findElement(titleInput).sendKeys(title);
    }

    public void enterPrice(String price) {
        driver.findElement(priceInput).sendKeys(price);
    }

    public void enterLocation(String location) {
        driver.findElement(locationInput).sendKeys(location);
    }

    public void enterDescription(String text) {
        WebElement element = driver.findElement(By.name("description"));

        ((JavascriptExecutor) driver).executeScript(
                "const textarea = arguments[0];" +
                        "const value = arguments[1];" + // use the text parameter
                        "textarea.scrollIntoView(true);" +
                        "textarea.focus();" +
                        "const descriptor = Object.getOwnPropertyDescriptor(Object.getPrototypeOf(textarea), 'value');" +
                        "if (descriptor && descriptor.set) {" +
                        "  descriptor.set.call(textarea, value);" +
                        "}" +
                        "textarea.dispatchEvent(new InputEvent('input', { bubbles: true, cancelable: true }));" +
                        "textarea.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "textarea.blur();", // optional to trigger validation
                element, text
        );

        System.out.println("Final value: '" + element.getAttribute("value") + "'");
    }

    public void clickSelectImage() {
        WebElement element = driver.findElement(selectImageBtn);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true); arguments[0].click();",
                element
        );
    }

    public void uploadImage(String filePath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(fileInput))
                .sendKeys(filePath);
    }

    public void submitProperty() { // after applying by class name (css selector) or xpath (type=submit), the solution is done by converting to javascript executor
        WebElement submit = wait.until(ExpectedConditions.presenceOfElementLocated(submitButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", submit
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
    }

}
