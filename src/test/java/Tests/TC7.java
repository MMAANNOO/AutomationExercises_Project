package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import Base.TestBase;
import Pages.HomePage;
import Pages.TestCasePage;

public class TC7 extends TestBase {
    @Test()
    public void testCasePage() {
        HomePage homePage = new HomePage(driver);
        TestCasePage testCasePage = new TestCasePage(driver);
        
        
        // Step 1: Launch browser (handled in TestBase setUp method)
        
        // Step 2: Navigate to url 'http://automationexercise.com'
       driver.get("http://automationexercise.com");
        
        // Step 3: Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), 
            "Home page should be visible");
        System.out.println("✓ Home page is visible successfully");
        
        // Step 4: Click on 'Test Case' button
        homePage.clickTestCaseButton();
        System.out.println("✓ Clicked on 'Test Case' button");
        
        // Step 5: Verify 'Test Case' is visible
        Assert.assertTrue(testCasePage.isTestCasesPageLoaded(), 
            "'GET IN TOUCH' text should be visible");
        System.out.println("✓ 'GET IN TOUCH' is visible");
    }
}
