package Tests;

import Base.TestBase;
import Pages.HomePage;
import Pages.ProductDetailPage;
import Pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC13 extends TestBase {

    @Test
    public void verifyProductAddedWithExactQuantity() {
        HomePage homePage = new HomePage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Step 1 & 2: Launch browser + Navigate to URL is handled in TestBase
        driver.get("http://automationexercise.com");
        
        // Step 3: Verify home page
        System.out.println("Step 3: Verify that home page is visible successfully");
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // Step 4: Click 'View Product'
        System.out.println("Step 4: Click 'View Product' for the first product");
        homePage.clickFirstViewProduct();

        // Step 5: Verify product detail is opened
        System.out.println("Step 5: Verify product detail page is opened");
        Assert.assertTrue(productDetailPage.isProductDetailPageVisible(),
                "Product detail page is not visible");

        // Step 6: Increase quantity to 4
        System.out.println("Step 6: Increase product quantity to 4");
        productDetailPage.setQuantity(4);

        // Step 7: Click 'Add to cart' button
        System.out.println("Step 7: Click 'Add to Cart'");
        productDetailPage.clickAddToCart();

        // Step 8: Click 'View Cart' button
        System.out.println("Step 8: Click 'View Cart'");
        productDetailPage.clickviewCart();

        // Step 9: Verify product in cart with quantity = 4
        System.out.println("Step 9: Verify product is displayed in cart with exact quantity 4");
        Assert.assertTrue(cartPage.isFirstProductInCart(), "No product found in cart");
        Assert.assertTrue(cartPage.verifyFirstProductQuantity("4"),
                "Product quantity in cart is not 4");
    }
}
