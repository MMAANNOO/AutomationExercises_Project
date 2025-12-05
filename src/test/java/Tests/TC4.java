package Tests;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.AccountDeletedPage;
import Utils.JSONUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC4 extends TestBase {
    
    @Test()
    public void testLoginWithValidCredentialsAndDeleteAccount() {
        // Initialize page objects
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver);
        
        // Step 1: Launch browser (handled in TestBase setUp method)
        
        // Step 2: Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        
        // Step 3: Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), 
            "Home page should be visible");
        System.out.println("✓ Home page is visible successfully");
        
        // Step 4: Click on 'Signup / Login' button
        homePage.clickLoginSignup();
        System.out.println("✓ Clicked on 'Signup / Login' button");
        
        // Step 5: Verify 'Login to your account' is visible
        Assert.assertTrue(loginPage.isLoginToYourAccountVisible(), 
            "'Login to your account' text should be visible");
        System.out.println("✓ 'Login to your account' is visible");
        
        // Step 6: Enter correct email address and password
        JSONObject testData = JSONUtils.getJSONData("src/test/java/DataReader/JSONReader.json");
        String email = testData.getJSONObject("validUser").getString("email");
        String password = testData.getJSONObject("validUser").getString("password");
        loginPage.enterLoginEmail(email);
        loginPage.enterLoginPassword(password);
        System.out.println("✓ Entered correct email and password");
        
        // Step 7: Click 'login' button
        loginPage.clickLoginButton();
        System.out.println("✓ Clicked login button");
        
        // Step 8: Verify that 'Logged in as username' is visible
        Assert.assertTrue(homePage.isLoggedInAsUserVisible(), 
            "'Logged in as username' text should be visible");
        
        // Step 9: Click 'Delete Account' button
        homePage.clickDeleteAccountButton();
        System.out.println("✓ Clicked 'Delete Account' button");
        
        // Step 10: Verify that 'ACCOUNT DELETED!' is visible
        Assert.assertTrue(accountDeletedPage.isAccountDeletedVisible(), 
            "'ACCOUNT DELETED!' message should be visible");
        System.out.println("✓ Account deleted successfully: " + accountDeletedPage.getAccountDeletedMessage());
        
        System.out.println("Test completed successfully!");
    }
}