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

public class TC19 extends TestBase {
    
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
    public void viewcartbrandproduct() {
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible");
         
        // Step 3: 
        homePage.clickProductsButton();

        // Step 4: 
        Assert.assertTrue(productPage.isBrandsVisible(), "Brands is NOT visible");

        // Step 5: 
        productPage.clickPoloBrandButton();
        
        // Step 6: 
        Assert.assertTrue(productPage.isPoloBrandPageVisible(), "Polo Brand Page is NOT visible");
        
        // Step 7: 
        productPage.clickMadameBrandButton();
        
        // Step 8: 
       Assert.assertTrue(productPage.isMadameBrandPageVisible(), "Madame Brand Page is NOT visible");
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
