package Tests;

import Base.TestBase;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC1 extends TestBase {

     @Test()
    public void testRegisterUser() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginSignup();

        SignupPage signupPage = new SignupPage(driver);
        Assert.assertTrue(signupPage.isNewUserTextVisible(), "New User Signup text not visible");

        String email = "manoo@mail.com";
        signupPage.enterNameAndEmail("Mahmoud", email);
        signupPage.clickSignup();

        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);
        accountCreationPage.fillAccountDetails("Password123#");
        accountCreationPage.clickCreateAccount();

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        Assert.assertTrue(accountCreatedPage.isAccountCreated(), "Account not created!");
        accountCreatedPage.clickContinue();

        Assert.assertTrue(homePage.isLoggedInAsUserVisible(), "User not logged in!");

       //driver.findElement(org.openqa.selenium.By.xpath("//a[contains(text(),'Delete Account')]")).click();
       //Assert.assertTrue(accountCreatedPage.isAccountDeleted(), "Account not deleted!");
    }
}
