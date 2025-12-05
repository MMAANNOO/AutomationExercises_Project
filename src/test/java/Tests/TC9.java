package Tests;

import Base.TestBase;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductDetailPage;
import Utils.JSONUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC9 extends TestBase {
    
    private HomePage homePage;
    private ProductPage productPage;
    private ProductDetailPage productDetailPage;
    
    @Test()
    public void SearchProduct() {
        
        // Step 1: Launch browser - handled in @BeforeMethod
        
        // Step 2: Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        
        // Step 3: Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), 
            "Home page is not visible successfully");
        System.out.println("✓ Home page is visible successfully");
        
        // Step 4: Click on 'Products' button
        homePage.clickProductsButton();
        System.out.println("✓ Clicked on Products button");
        
        // Step 5: Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(productPage.isAllProductsPageVisible(), 
            "User is not navigated to ALL PRODUCTS page successfully");
        System.out.println("✓ User is navigated to ALL PRODUCTS page successfully");
        
       JSONObject testData = JSONUtils.getJSONData("src/test/java/DataReader/JSONReader.json");
        String name = testData.getJSONObject("ProductName").getString("name");
        productPage.enterProductName(name);      
        productPage.clickProductSearchButton();
    }
}
