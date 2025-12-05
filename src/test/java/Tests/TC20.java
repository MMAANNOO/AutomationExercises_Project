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

public class TC20 extends TestBase {
    
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private SignupPage signupPage;
    
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        signupPage = new SignupPage(driver);
    }
    
    @Test
    public void searchproductandverifycartafterlogin() {
        
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible");
        
        homePage.clickProductsButton();
        
        Assert.assertTrue(productPage.isAllProductsPageVisible(), "ALL PRODUCTS page is NOT visible");
        
        JSONObject testData = JSONUtils.getJSONData("src/test/java/DataReader/JSONReader.json");
        String name = testData.getJSONObject("ProductName").getString("name");
        productPage.enterProductName(name);      
        productPage.clickProductSearchButton();
        
        Assert.assertTrue(productPage.isSearchedProductPageVisible(), "Searched Product page is NOT visible");
        
        productPage.addFirstProductToCart();
        
        productPage.clickViewCartButton();
        
        Assert.assertTrue(cartPage.isCartPageVisible(), "Cart page is NOT visible");
        
        driver.findElement(org.openqa.selenium.By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")).click();
        
        String email = "maaanooo@mail.com";
        signupPage.enterNameAndEmail("Mahmoud", email);
        signupPage.clickSignup();
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);
        accountCreationPage.fillAccountDetails("Password123#");
        accountCreationPage.clickCreateAccount();

        Assert.assertTrue(homePage.isAccountCreatedVisible(), "ACCOUNT CREATED! not visible");
        homePage.clickContinueAfterAccountCreation();

        Assert.assertTrue(homePage.isLoggedInAsUserVisible(), "Logged in as user not visible");

        homePage.clickCartButton();
        
        Assert.assertTrue(cartPage.isCartPageVisible(), "Cart page is NOT visible");
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
