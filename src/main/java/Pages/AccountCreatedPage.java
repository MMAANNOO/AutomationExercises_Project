package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage {
    private WebDriver driver;

    private By accountCreatedMsg = By.xpath("//b[contains(text(),'Account Created!')]");
    private By continueBtn = By.xpath("//a[contains(text(),'Continue')]");
    private By accountDeletedMsg = By.xpath("//b[contains(text(),'Account Deleted!')]");

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    // Verify Account Created
    public boolean isAccountCreated() {
        return driver.findElement(accountCreatedMsg).isDisplayed();
    }

    // Click Continue
    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }

    // Verify Account Deleted
    public boolean isAccountDeleted() {
        return driver.findElement(accountDeletedMsg).isDisplayed();
    }
}
