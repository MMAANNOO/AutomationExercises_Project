package Tests;

import Base.TestBase;
import Pages.AccountCreationPage;
import Pages.HomePage;
import Pages.CartPage;
import Pages.ProductDetailPage;
import Pages.SignupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class TC14 extends TestBase {

    private WebDriver driver;
    private HomePage homePage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;
    private SignupPage signupPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        productDetailPage = new ProductDetailPage(driver);
        cartPage = new CartPage(driver);
        signupPage = new SignupPage(driver);
    }

    @Test
    public void placeOrderAndDeleteAccountwhilecheckout() {
        // Step 3: Verify home page is visible
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible");

        // Step 4: Add product to cart
        homePage.clickFirstViewProduct();
        Assert.assertTrue(productDetailPage.isProductDetailPageVisible(), "Product detail is NOT visible");
        productDetailPage.clickAddToCart();

        // Step 5: Click Cart button
        productDetailPage.clickviewCart();

        // Step 7: Click Proceed To Checkout
        cartPage.clickProceedToCheckout();

        // Step 8: Click 'Register / Login'
        cartPage.clickRegisterLoginButton();

        // Step 9: Fill details in Signup form
        String email = "manoooo@mail.com";
        signupPage.enterNameAndEmail("Mahmoud", email);
        signupPage.clickSignup();
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);
        accountCreationPage.fillAccountDetails("Password123#");
        accountCreationPage.clickCreateAccount();

        // Assume Account Information Page handled in SignupPage (extend later if needed)

        // Step 10: Verify 'ACCOUNT CREATED!' and click Continue
        Assert.assertTrue(homePage.isAccountCreatedVisible(), "ACCOUNT CREATED! not visible");
        homePage.clickContinueAfterAccountCreation();

        // Step 11: Verify 'Logged in as username'
        Assert.assertTrue(homePage.isLoggedInAsUserVisible(), "Logged in as user not visible");

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
       // homePage.clickDeleteAccountButton();

        // Step 20: Verify 'ACCOUNT DELETED!' and click Continue
        //Assert.assertTrue(homePage.isAccountDeletedMessageVisible(),
         //       "ACCOUNT DELETED! not visible");
        //homePage.clickContinueAfterAccountDeletion();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
