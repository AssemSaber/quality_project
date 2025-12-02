package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PendingProperties;

import java.time.Duration;

public class PendingPropertiesTest {
    WebDriver driver;
    PendingProperties pending;
    LoginPage loginPage;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
        loginPage=new LoginPage(driver);
        pending = new PendingProperties(driver);
    }

    @Test
    public void approveProperty(){
        loginPage.openLoginPage();
        loginPage.enterEmail("assem@admin.com");
        loginPage.enterPassword("123456789");
        loginPage.clickLogin();

        // waiting for 10 sec and  adjusting url for LoginPage and PendingProperties
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/Admin"));

        pending.clickPendingProperties();
        pending.clickApprove();
    }
    @Test
    public void cancelProperty(){
        loginPage.openLoginPage();
        loginPage.enterEmail("assem@admin.com");
        loginPage.enterPassword("123456789");
        loginPage.clickLogin();

        // waiting for 10 sec and  adjusting url for LoginPage and PendingProperties to see the current url
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/Admin"));

        pending.clickPendingProperties();
        pending.clickReject();
    }
//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }

}
