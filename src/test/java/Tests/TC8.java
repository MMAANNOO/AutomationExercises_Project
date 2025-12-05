package Tests;

import Base.TestBase;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductDetailPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC8 extends TestBase {
    
    private HomePage homePage;
    private ProductPage productPage;
    private ProductDetailPage productDetailPage;
    
    @Test()
    public void verifyProductDetailsTest() {
        
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
        
        // Step 6: The products list is visible
        Assert.assertTrue(productPage.isProductsListVisible(), 
            "Products list is not visible");
        System.out.println("✓ Products list is visible");
        System.out.println("✓ Total products found: " + productPage.getProductsCount());
        
        // Step 7: Click on 'View Product' of first product
        productPage.clickViewProductOfFirstProduct();
        System.out.println("✓ Clicked on 'View Product' of first product");
        
        // Step 8: User is landed to product detail page
        Assert.assertTrue(productDetailPage.isProductDetailPageVisible(), 
            "User is not landed to product detail page");
        System.out.println("✓ User is landed to product detail page");
        
        // Step 9: Verify that detail is visible: product name, category, price, availability, condition, brand
        Assert.assertTrue(productDetailPage.isProductNameVisible(), 
            "Product name is not visible");
        System.out.println("✓ Product name is visible: " + productDetailPage.getProductName());
        
        Assert.assertTrue(productDetailPage.isProductCategoryVisible(), 
            "Product category is not visible");
        System.out.println("✓ Product category is visible: " + productDetailPage.getProductCategory());
        
        Assert.assertTrue(productDetailPage.isProductPriceVisible(), 
            "Product price is not visible");
        System.out.println("✓ Product price is visible: " + productDetailPage.getProductPrice());
        
        Assert.assertTrue(productDetailPage.isProductAvailabilityVisible(), 
            "Product availability is not visible");
        System.out.println("✓ Product availability is visible: " + productDetailPage.getProductAvailability());
        
        Assert.assertTrue(productDetailPage.isProductConditionVisible(), 
            "Product condition is not visible");
        System.out.println("✓ Product condition is visible: " + productDetailPage.getProductCondition());
        
        Assert.assertTrue(productDetailPage.isProductBrandVisible(), 
            "Product brand is not visible");
        System.out.println("✓ Product brand is visible: " + productDetailPage.getProductBrand());
        
        // Overall verification
        Assert.assertTrue(productDetailPage.areAllProductDetailsVisible(), 
            "All product details are not visible");
        System.out.println("✓ All product details are visible successfully");
        
        System.out.println("========================================");
        System.out.println("TEST CASE TC8 COMPLETED SUCCESSFULLY!");
        System.out.println("========================================");
    }
}