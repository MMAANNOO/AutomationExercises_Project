package Tests;

import Base.TestBase;
import Pages.*;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TC25 extends TestBase {
    
    private WebDriver driver;
    private HomePage homePage;
    
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
    }
    
    @Test
    public void verifyScrollUpwitharrow() {
        
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible");
        
        homePage.scrollToSubscription();
        
        Assert.assertTrue(homePage.isSubscriptionTextVisible(), "Subscription text is NOT visible");
        
        homePage.clickScrollUpArrowButton();
        
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible");
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
