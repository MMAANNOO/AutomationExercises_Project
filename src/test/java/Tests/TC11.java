package Tests;

import Base.TestBase;
import Pages.CartPage;
import Pages.HomePage;
import Utils.JSONUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC11 extends TestBase {
    
    private HomePage homePage;
    private CartPage cartPage;

    @Test
    public void verifySubscriptionCartPage() {
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);

        // Step 3: Verify homepage is visible
        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page not visible");

        // Step 4: Click 'Cart' button
        cartPage.clickProceedToCheckout();

        // Step 5: Scroll down to footer
        homePage.scrollToSubscription();

        // Step 6: Verify 'SUBSCRIPTION' text
        Assert.assertTrue(homePage.isSubscriptionTextVisible(), "❌ 'SUBSCRIPTION' text not visible");

        // Step 7: Enter email and click arrow button
        JSONObject testData = JSONUtils.getJSONData("src/test/java/DataReader/JSONReader.json");
        String email = testData.getJSONObject("VerifySubscribtionCartPage").getString("email");
        homePage.enterEmailAndSubscribe(email);

        // Step 8: Verify success message
        Assert.assertTrue(homePage.isSuccessMessageVisible(),
                "❌ Success message not displayed after subscribing");
    }
}
