package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PendingProperties {
    WebDriver driver;
    public PendingProperties(WebDriver driver) {
        this.driver = driver;
    }

    By pendingPropertiesBtn = By.xpath("//button[contains(., 'Pending Properties')]");
    By approveBtn = By.xpath("//button[contains(., 'Approve')]");
    By reject = By.xpath("//button[@class='reject-btn']");
    public void clickPendingProperties() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Pending Properties')]")));
        btn.click();
    }

    public void clickApprove() {
        driver.findElement(approveBtn).click();
    }
    public void clickReject() {
        driver.findElement(reject).click();
    }

}
