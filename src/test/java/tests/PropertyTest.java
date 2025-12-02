package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PropertyPage;

public class PropertyTest {
    WebDriver driver;
    LoginPage loginPage;
    PropertyPage propertyPage;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
//        driver.get("http://localhost:3000/");
//        loginPage = new LoginPage(driver);
        driver.get("http://localhost:3000/add-property");
        propertyPage=new PropertyPage(driver);
    }
    @Test
    public void inValidPrice(){
//        loginPage.openLoginPage();
//        loginPage.enterEmail("you@gmail.com");
//        loginPage.enterPassword("12345");
//        loginPage.clickLogin();
        propertyPage.enterTitle("rent mate ALEX");
        propertyPage.enterPrice("-9");
        propertyPage.enterLocation("alex");
        propertyPage.enterDescription("buy and take an offer");

//        propertyPage.clickSelectImage();
        propertyPage.uploadImage("H:\\elon.jpg");

        propertyPage.submitProperty();

//        System.out.println("Property added successfully!");

    }

}
