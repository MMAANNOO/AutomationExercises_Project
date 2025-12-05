package Tests;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Utils.JSONUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC3 extends TestBase {
    
    @Test()
    public void testLoginWithIncorrectCredentials() {
        // Initialize page objects
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Step 1: Launch browser (handled in TestBase setUp method)
        
        // Step 2: Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        
        // Step 3: Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), 
            "Home page should be visible");
        System.out.println("✓ Home page is visible successfully");
        
        // Step 4: Click on 'Signup / Login' button
        homePage.clickLoginSignup();
        
        // Step 5: Verify 'Login to your account' is visible
        Assert.assertTrue(loginPage.isLoginToYourAccountVisible(), 
            "'Login to your account' text should be visible");
        System.out.println("✓ 'Login to your account' is visible");
        
        // Step 6: Enter incorrect email address and password
        JSONObject testData = JSONUtils.getJSONData("src/test/java/DataReader/JSONReader.json");
        String email = testData.getJSONObject("invalidUser").getString("email");
        String password = testData.getJSONObject("invalidUser").getString("password");
        loginPage.enterLoginEmail(email);
        loginPage.enterLoginPassword(password);
        System.out.println("✓ Entered incorrect email and password");
        
        // Step 7: Click 'login' button
        loginPage.clickLoginButton();
        System.out.println("✓ Clicked login button");
        
        // Step 8: Verify error 'Your email or password is incorrect!' is visible
        Assert.assertTrue(loginPage.isErrorMessageVisible(), 
            "Error message 'Your email or password is incorrect!' should be visible");
        System.out.println("✓ Error message is visible: " + loginPage.getErrorMessage());
        
        System.out.println("Test completed successfully!");
    }
}