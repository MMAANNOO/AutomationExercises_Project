package Tests;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Utils.JSONUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC5 extends TestBase {
    
    @Test()
    public void testSignupWithExistingEmail() {
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
        System.out.println("✓ Clicked on 'Signup / Login' button");
        
        // Step 5: Verify 'New User Signup!' is visible
        Assert.assertTrue(loginPage.isNewUserSignupVisible(), 
            "'New User Signup!' text should be visible");
        System.out.println("✓ 'New User Signup!' is visible");
        
        // Step 6: Enter name and already registered email address
        JSONObject testData = JSONUtils.getJSONData("src/test/java/DataReader/JSONReader.json");
        String name = testData.getJSONObject("existingUser").getString("name");
        String email = testData.getJSONObject("existingUser").getString("email");
        loginPage.enterSignupName(name);
        loginPage.enterSignupEmail(email);
        
        // Step 7: Click 'Signup' button
        loginPage.clickSignupButton();
        System.out.println("✓ Clicked 'Signup' button");
        
        // Step 8: Verify error 'Email Address already exist!' is visible
        Assert.assertTrue(loginPage.isSignupErrorMessageVisible(), 
            "Error message 'Email Address already exist!' should be visible");
        System.out.println("Test completed successfully!");
    }
}