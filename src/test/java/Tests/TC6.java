package Tests;

import Base.TestBase;
import Pages.HomePage;
import Pages.ContactUsPage;
import Utils.JSONUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class TC6 extends TestBase {
    
    @Test()
    public void testContactUsFormSubmission() {
        // Initialize page objects
        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        
        // Create a test file to upload
        String testFilePath = createTestFile();
        
        // Step 1: Launch browser (handled in TestBase setUp method)
        
        // Step 2: Navigate to url 'http://automationexercise.com'
       driver.get("http://automationexercise.com");
        
        // Step 3: Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), 
            "Home page should be visible");
        System.out.println("✓ Home page is visible successfully");
        
        // Step 4: Click on 'Contact Us' button
        homePage.clickContactUsButton();
        System.out.println("✓ Clicked on 'Contact Us' button");
        
        // Step 5: Verify 'GET IN TOUCH' is visible
        Assert.assertTrue(contactUsPage.isGetInTouchVisible(), 
            "'GET IN TOUCH' text should be visible");
        System.out.println("✓ 'GET IN TOUCH' is visible");
        
        // Step 6: Enter name, email, subject and message
        JSONObject testData = JSONUtils.getJSONData("src/test/java/DataReader/JSONReader.json");
        String name = testData.getJSONObject("ContactForm").getString("name");
        String email = testData.getJSONObject("ContactForm").getString("email");
        String subject = testData.getJSONObject("ContactForm").getString("subject");
        String message = testData.getJSONObject("ContactForm").getString("message");
        contactUsPage.fillContactForm(name, email, subject, message);
        
        // Step 7: Upload file
        if (testFilePath != null) {
            contactUsPage.uploadFile(testFilePath);
            System.out.println("✓ File uploaded: " + testFilePath);
        }
        
        // Step 8: Click 'Submit' button
        contactUsPage.clickSubmitButton();
        System.out.println("✓ Clicked 'Submit' button");
        
        // Step 9: Click OK button (handle alert)
        contactUsPage.handleAlert();
        System.out.println("✓ Handled alert - Clicked OK button");
        
        // Step 10: Verify success message 'Success! Your details have been submitted successfully.' is visible
        Assert.assertTrue(contactUsPage.isSuccessMessageVisible(), 
            "Success message should be visible after form submission");
        System.out.println("✓ Success message displayed: " + contactUsPage.getSuccessMessage());
        
        // Step 11: Click 'Home' button and verify that landed to home page successfully
        contactUsPage.clickHomeButton();
        System.out.println("✓ Clicked 'Home' button");
        
        // Verify we're back on the home page
        Assert.assertTrue(homePage.isHomePageVisible(), 
            "Should be back on home page after clicking Home button");
        System.out.println("✓ Successfully navigated back to home page");
        
        // Clean up the test file
        cleanupTestFile(testFilePath);
        
        System.out.println("✓ Contact Us test completed successfully!");
    }
    
    // Helper method to create a test file for upload
    private String createTestFile() {
        try {
            // Create a temporary test file
            File testFile = File.createTempFile("test_upload", ".txt");
            FileWriter writer = new FileWriter(testFile);
            writer.write("This is a test file for automation testing.\n");
            writer.write("Created for Contact Us form file upload test.\n");
            writer.write("Date: " + new java.util.Date().toString());
            writer.close();
            
            String filePath = testFile.getAbsolutePath();
            System.out.println("✓ Created test file: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.out.println("Failed to create test file: " + e.getMessage());
            return null;
        }
    }
    
    // Helper method to clean up test file
    private void cleanupTestFile(String filePath) {
        if (filePath != null) {
            try {
                File file = new File(filePath);
                if (file.exists() && file.delete()) {
                    System.out.println("✓ Cleaned up test file: " + filePath);
                }
            } catch (Exception e) {
                System.out.println("Failed to cleanup test file: " + e.getMessage());
            }
        }
    }
}