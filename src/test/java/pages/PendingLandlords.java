package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PendingLandlords {
    WebDriver driver;
    public PendingLandlords(WebDriver driver) {
        this.driver = driver;
    }
    By pendingPropertiesBtn = By.xpath("//button[contains(., 'Pending Landlords')]");
    By approveBtn = By.xpath("//button[@class='approve-btn']");
    By reject = By.xpath("//button[@class='reject-btn']");

    public void clickApprove() {
        driver.findElement(approveBtn).click();
    }
    public void clickReject() {
        driver.findElement(reject).click();
    }

}
