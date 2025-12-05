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

public class TC18 extends TestBase {
    
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
    }
    
    @Test
    public void viewcategoryproduct() {
        // Step 3: 
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible");

        // Step 4: 
        Assert.assertTrue(homePage.isCategoryNameVisible(), "Category Name is NOT visible");

        // Step 5: 
        homePage.clickWomenProductButton();
        
        // Step 6: 
        homePage.clickDressWomenProductButton();
        
        // Step 7: 
        Assert.assertTrue(productPage.isWomenPageVisible(), "Women Page is NOT visible");
        
        // Step 8: 
        productPage.clickMenProductButton();
        
        // Step 9:
        productPage.clickTshirtsMenProductButton();
        
        // Step 10:
        Assert.assertTrue(productPage.isMenPageVisible(), "Men Page is NOT visible");
    }
     @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
