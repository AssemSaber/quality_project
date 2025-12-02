package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By emailInput = By.xpath("//input[@placeholder='Email']");
    By passwordInput = By.xpath("//input[@placeholder='Password']");
    By loginBtn = By.xpath("//button[@type='submit']");
    By loginLink = By.xpath("//a[@href='/login']");
    // Actions (Methods)
    public void openLoginPage() {
        driver.findElement(loginLink).click();
    }
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String pass) {
        driver.findElement(passwordInput).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }
}
