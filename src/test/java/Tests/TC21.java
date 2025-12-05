package Tests;

import Base.TestBase;
import Pages.*;
import Utils.JSONUtils;
import java.time.Duration;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TC21 extends TestBase {
    
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private ProductDetailPage productDetailPage;
    
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        productDetailPage = new ProductDetailPage(driver);
    }
    
    @Test
    public void addreviewonproduct() {
        
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible");
        
        homePage.clickProductsButton();
        
        Assert.assertTrue(productPage.isAllProductsPageVisible(), "ALL PRODUCTS page is NOT visible");
        
        productPage.hoverOverFirstProduct();
        productPage.clickViewProductButton();
        
        Assert.assertTrue(productDetailPage.isProductDetailPageVisible(), "Products Detail page is NOT visible");
        
        JSONObject testData = JSONUtils.getJSONData("src/test/java/DataReader/JSONReader.json");
        String name = testData.getJSONObject("ProductDetail").getString("name");
        String email = testData.getJSONObject("ProductDetail").getString("email");
        String review = testData.getJSONObject("ProductDetail").getString("review");
        productDetailPage.enterYourName(name);
        productDetailPage.enterYourEmail(email);
        productDetailPage.enterYourReview(review);
        productDetailPage.clickSubmitButton();
        
        Assert.assertTrue(productDetailPage.isSubmitMessageVisible(), "Submit Message is NOT visible");
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
