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

public class TC17 extends TestBase{
    
    private WebDriver driver;
    private HomePage homePage;
    private CartPage cartPage;
    private ProductDetailPage productDetailPage;
    
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        productDetailPage = new ProductDetailPage(driver);
        cartPage = new CartPage(driver);
    }
    
    @Test
    public void removeproductsfromcart() {
        // Step 3: Verify home page is visible
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible");

        // Step 4: Add product to cart
        homePage.clickFirstViewProduct();
        Assert.assertTrue(productDetailPage.isProductDetailPageVisible(), "Product detail is NOT visible");
        productDetailPage.clickAddToCart();

        // Step 5: Click Cart button
        productDetailPage.clickviewCart();
        
        // Step 6: Cart page visible
        Assert.assertTrue(cartPage.isCartPageVisible());
        
        // Step 7: Remove product from Cart 
        cartPage.clickRemoveProduct();
        
        // Step 8: Cart is Empty
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not Empty");
    }
     @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
