package Tests;

import Base.TestBase;
import Pages.*;
import Utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TC12 extends TestBase {
    
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    
    @BeforeMethod
    public void setUp() {
        // Launch browser
        driver = DriverFactory.getDriver();
        
        // Initialize page objects
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }
    
    @Test
    public void testAddMultipleProductsToCartDebug() {
        try {
            
            // Navigate to url 'http://automationexercise.com'
            System.out.println("Step 1: Navigating to automationexercise.com");
            driver.get("http://automationexercise.com");
            
            // Verify that home page is visible successfully
            System.out.println("Step 2: Verifying home page visibility");
            boolean isHomePageVisible = homePage.isHomePageVisible();
            System.out.println("Home page visible: " + isHomePageVisible);
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page title: " + driver.getTitle());
            Assert.assertTrue(isHomePageVisible, "Home page should be visible successfully");
            
            // Click 'Products' button
            System.out.println("Step 3: Clicking Products button");
            homePage.clickProductsButton();
            
            // Verify navigation to products page
            System.out.println("Step 4: Verifying products page visibility");
            boolean isProductsPageVisible = productPage.isProductsPageVisible();
            System.out.println("Products page visible: " + isProductsPageVisible);
            System.out.println("Current URL after clicking Products: " + driver.getCurrentUrl());
            Assert.assertTrue(isProductsPageVisible, "Products page should be visible after clicking Products button");
            
            // Debug: Check what add to cart buttons are available
            System.out.println("=== DEBUGGING ADD TO CART BUTTONS ===");
            List<WebElement> allAddToCartButtons = driver.findElements(By.cssSelector(".add-to-cart"));
            System.out.println("Found " + allAddToCartButtons.size() + " add-to-cart buttons using CSS selector");
            
            List<WebElement> addToCartByXpath = driver.findElements(By.xpath("//a[contains(@class,'add-to-cart')]"));
            System.out.println("Found " + addToCartByXpath.size() + " add-to-cart buttons using XPath");
            
            List<WebElement> addToCartByText = driver.findElements(By.xpath("//a[contains(text(),'Add to cart')]"));
            System.out.println("Found " + addToCartByText.size() + " add-to-cart buttons using text search");
            
            // Check if products are visible
            List<WebElement> productCards = driver.findElements(By.cssSelector(".col-sm-4"));
            System.out.println("Found " + productCards.size() + " product cards");
            
            if (productCards.size() >= 2) {
                System.out.println("First product card is displayed: " + productCards.get(0).isDisplayed());
                System.out.println("Second product card is displayed: " + productCards.get(1).isDisplayed());
            }
            
            // Print current page source (truncated) for debugging
            String pageSource = driver.getPageSource();
            if (pageSource.contains("add-to-cart") || pageSource.contains("Add to cart")) {
                System.out.println("Page source DOES contain add-to-cart elements");
            } else {
                System.out.println("Page source does NOT contain add-to-cart elements");
            }
            
            System.out.println("=== END DEBUGGING SECTION ===");
            
            // Hover over first product and click 'Add to cart'
            System.out.println("Step 5: Hovering over first product and adding to cart");
            productPage.hoverAndAddFirstProductToCart();
            System.out.println("Successfully added first product to cart");
            
            // Click 'Continue Shopping' button
            System.out.println("Step 6: Clicking Continue Shopping");
            productPage.clickContinueShopping();
            System.out.println("Successfully clicked Continue Shopping");
            
            // Hover over second product and click 'Add to cart'
            System.out.println("Step 7: Hovering over second product and adding to cart");
            productPage.hoverAndAddSecondProductToCart();
            System.out.println("Successfully added second product to cart");
            
            // Click 'View Cart' button
            System.out.println("Step 8: Clicking View Cart");
            productPage.clickViewCart();
            System.out.println("Current URL after clicking View Cart: " + driver.getCurrentUrl());
            
            // Verify both products are added to Cart
            System.out.println("Step 9: Verifying products in cart");
            
            // Check cart page visibility
            boolean isCartVisible = cartPage.isCartPageVisible();
            System.out.println("Cart page visible: " + isCartVisible);
            
            // Now check for products
            boolean firstProductInCart = cartPage.isFirstProductInCart();
            boolean secondProductInCart = cartPage.isSecondProductInCart();
            
            System.out.println("First product in cart: " + firstProductInCart);
            System.out.println("Second product in cart: " + secondProductInCart);
            
            Assert.assertTrue(firstProductInCart, "First product should be present in cart");
            Assert.assertTrue(secondProductInCart, "Second product should be present in cart");
            
        } catch (Exception e) {
            System.out.println("=== TEST FAILED WITH EXCEPTION ===");
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page title: " + driver.getTitle());
            e.printStackTrace();
            
            // Take a screenshot if possible
            try {
                System.out.println("Page source contains 'add-to-cart': " + 
                    driver.getPageSource().contains("add-to-cart"));
                System.out.println("Page source contains 'Add to cart': " + 
                    driver.getPageSource().contains("Add to cart"));
            } catch (Exception ex) {
                System.out.println("Could not check page source: " + ex.getMessage());
            }
            
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            // Wait a bit before closing for debugging
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            driver.quit();
        }
    }
}