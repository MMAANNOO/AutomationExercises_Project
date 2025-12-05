package Tests;

import Base.TestBase;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductDetailPage;
import Utils.JSONUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC10 extends TestBase{
    
    private HomePage homePage;
    
    @Test()
    public void VerifySubscribtion() {
        // Step 1: Launch browser - handled in @BeforeMethod
        
        // Step 2: Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        homePage = new HomePage(driver);
        
        // Step 3: Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), 
            "Home page is not visible successfully");
        System.out.println("✓ Home page is visible successfully");
        
        // Step 4: Scroll down to footer
        homePage.scrollToSubscription();

        // Step 5: Verify 'SUBSCRIPTION' text
        Assert.assertTrue(homePage.isSubscriptionTextVisible(), "❌ 'SUBSCRIPTION' text not visible");

        // Step 6: Enter email and click arrow button
        JSONObject testData = JSONUtils.getJSONData("src/test/java/DataReader/JSONReader.json");
        String email = testData.getJSONObject("VerifySubscribtion").getString("email");
        homePage.enterEmailAndSubscribe(email);

        // Step 7: Verify success message
        Assert.assertTrue(homePage.isSuccessMessageVisible(),
                "❌ Success message not displayed after subscribing");
    }
}
