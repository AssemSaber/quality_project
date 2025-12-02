package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;


// cd C:\Users\Moham\IdeaProjects\QA
//  mvn -Dtest=LoginTest#InValidPassword test

public class LoginTest {

        WebDriver driver;
        LoginPage loginPage;

        @BeforeMethod
        public void setUp() {
            driver = new ChromeDriver();
            driver.get("http://localhost:3000/");
            loginPage = new LoginPage(driver);
        }

        @Test
        public void InValidPassword() {
            loginPage.openLoginPage();
            loginPage.enterEmail("assem@admin.com");
            loginPage.enterPassword("1234567");
            loginPage.clickLogin();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            try {
                // there is alert appears in case of wrong login , so we deal with it
                // if there is no alert, he login successfully and doesn't convert alert msg that is error here
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                System.out.println("Alert says: " + alertText);
                Assert.assertEquals(alertText,  "\"Invalid password\"");
                System.out.println(" failed to login due to password");

                alert.accept();

            } catch (TimeoutException e) {

                System.out.println("Login successfully");

            }
        }
    @Test
    public void InValidEmail() {
        loginPage.openLoginPage();
        loginPage.enterEmail("assemasd@gmail.com");
        loginPage.enterPassword("1234567");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            // there is alert appears in case of wrong login , so we deal with it
            // if there is no alert, he login successfully and doesn't convert alert msg that is error here
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert says: " + alertText);
            Assert.assertEquals(alertText,  "\"Email not found\"");
            System.out.println(" failed to login due to Email");

            alert.accept();

        } catch (TimeoutException e) {

            System.out.println("put an Email well-formated");

        }
    }
    @Test
    public void ValidLogin() {
        loginPage.openLoginPage();
        loginPage.enterEmail("assem@admin.com");
        loginPage.enterPassword("123456789");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            // there is alert appears in case of wrong login , so we deal with it
            // if there is no alert, he log in successfully and when it doesn't convert alert msg that is error here
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert says: " + alertText);
            Assert.assertEquals(alertText,  "\"Email not found\"");
            System.out.println(" failed to login due to Email");

            alert.accept();

        } catch (TimeoutException e) {

            System.out.println("login successfully");

        }
    }

        @AfterMethod
        public void tearDown() {
            driver.quit();
        }

}
