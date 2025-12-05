package Tests;

import Base.TestBase;
import Pages.HomePage;
import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductDetailPage;
import Utils.JSONUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import org.json.JSONObject;

public class TC16 extends TestBase {
    
    private WebDriver driver;
    private HomePage homePage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        productDetailPage = new ProductDetailPage(driver);
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);
    }
     @Test
    public void placeOrderAndDeleteAccountbeforecheckout() {
        // Step 3: Verify home page is visible
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible");

        // Step 4: Add product to cart
        homePage.clickFirstViewProduct();
        Assert.assertTrue(productDetailPage.isProductDetailPageVisible(), "Product detail is NOT visible");
        productDetailPage.clickAddToCart();

        // Step 5: Click Cart button
        productDetailPage.clickviewCart();
        
        
        // Step 8: Click 'Register / Login'
        driver.findElement(org.openqa.selenium.By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")).click();
        
        // Step 9: Fill details in Signup form
        JSONObject testData = JSONUtils.getJSONData("src/test/java/DataReader/JSONReader.json");
        String email = testData.getJSONObject("validUser").getString("email");
        String password = testData.getJSONObject("validUser").getString("password");
        loginPage.enterLoginEmail(email);
        loginPage.enterLoginPassword(password);
        loginPage.clickLoginButton();
        System.out.println("✓ Entered correct email and password");

        // Assume Account Information Page handled in SignupPage (extend later if needed)

        // Step 12: Click Cart button again
        homePage.clickCartButton();

        // Step 13: Proceed To Checkout again
        cartPage.clickProceedToCheckout();

        // Step 14: Verify Address Details and Review Your Order
        Assert.assertTrue(cartPage.isAddressDetailsVisible(), "Address details not visible");
        Assert.assertTrue(cartPage.isReviewOrderVisible(), "Review order not visible");

        // Step 15: Enter description & Place Order
        cartPage.enterOrderComment("Please deliver ASAP");
        cartPage.clickPlaceOrder();

        // Step 16: Enter payment details
        cartPage.enterPaymentDetails("Test User", "4111111111111111", "123", "12", "2030");

        // Step 17: Pay and confirm order
        cartPage.clickPayAndConfirm();

        // Step 18: Verify success message
        Assert.assertTrue(cartPage.isOrderSuccessMessageVisible(),
                "Order success message not displayed");

        // Step 19: Delete Account
        homePage.clickDeleteAccountButton();

        // Step 20: Verify 'ACCOUNT DELETED!' and click Continue
        Assert.assertTrue(homePage.isAccountDeletedMessageVisible(),
                "ACCOUNT DELETED! not visible");
        homePage.clickContinueAfterAccountDeletion();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
