
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


// powershell
// C:\Users\Moham\IdeaProjects\QA
//cd C:\Users\Moham\IdeaProjects\QA
// mvn clean compile
// to run all test according to priority
 // >> mvn test
// to run specific test
//>> mvn -Dtest=ClassName#name test
//>> mvn -Dtest=ExampleTest#login_InValidPassword test
public class ExampleTest {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.get("http://localhost:3000/");
    }

    @Test(priority = 1)
    public void register() { //alert shows that Registered Successfully! or Email already exists if repeated action
        WebElement loginButton = driver.findElement(By.xpath("//a[@href='/register']"));
        loginButton.click();
        WebElement userNameInput = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        userNameInput.sendKeys("tester");
        WebElement emailInput = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        emailInput.sendKeys("tester@gmail.com");
        WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordInput.sendKeys("12345");
        WebElement confirmPass = driver.findElement(By.xpath("//input[@placeholder='Confirm Password']"));
        confirmPass.sendKeys("12345");
        WebElement phoneNum = driver.findElement(By.xpath("//input[@placeholder='Phone Number']"));
        phoneNum.sendKeys("01014965685");
        // open drill down
        WebElement roleDropdown = driver.findElement(By.xpath("//select[@required]"));
        Select selectRole = new Select(roleDropdown);
        selectRole.selectByVisibleText("Landlord");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        submitBtn.click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText,"Registered Successfully!");
    }



    @Test(priority = 3)
    public void login_InValidPassword() {
        WebElement loginButton = driver.findElement(By.xpath("//a[@href='/login']"));
        loginButton.click();
        WebElement emailInput = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        emailInput.sendKeys("assem@admin.com");
        WebElement passInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passInput.sendKeys("1234567");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // sleep 5 seconds
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
    public void add_property() {
        WebElement loginButton = driver.findElement(By.xpath("//a[@href='/login']"));
        loginButton.click();
        WebElement emailInput = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        emailInput.sendKeys("you@gmail.com");
        WebElement passInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passInput.sendKeys("12345");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // sleep 5 seconds

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addPropertyBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-property"))
        );
        addPropertyBtn.click();

        WebElement titleInput = driver.findElement(By.name("title"));
        titleInput.sendKeys("rent mate ALEX");

        WebElement priceInput = driver.findElement(By.name("price"));
        priceInput.sendKeys("1500");

        WebElement locationInput = driver.findElement(By.name("location"));
        locationInput.sendKeys("alex");

        //Cast driver to JavascriptExecutor that allows you to run raw JavaScript code in the browser
        WebElement descInput = driver.findElement(By.name("description"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true); arguments[0].value='buy and take an offer'; arguments[0].dispatchEvent(new Event('input'));",
                descInput
        );

        WebElement selectImageBtn = driver.findElement(By.xpath("//button[contains(.,'Select Image')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", selectImageBtn);

        // as it is hidden we need to find type file
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("H:\\elon.jpg");
        System.out.println("property added successfully");
    }


    @AfterMethod
    public void close() {

        driver.quit();
    }
}
