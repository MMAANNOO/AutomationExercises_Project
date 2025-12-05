package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class AccountDeletedPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    public AccountDeletedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    // Page Elements
    @FindBy(css = "h2[data-qa='account-deleted']")
    private WebElement accountDeletedHeader;
    
    @FindBy(xpath = "//h2[contains(text(), 'ACCOUNT DELETED!')]")
    private WebElement accountDeletedText;
    
    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueButton;
    
    // Page Actions
    public boolean isAccountDeletedVisible() {
        try {
            // Try both selectors to ensure we catch the element
            WebElement element = null;
            try {
                element = wait.until(ExpectedConditions.visibilityOf(accountDeletedHeader));
            } catch (Exception e) {
                element = wait.until(ExpectedConditions.visibilityOf(accountDeletedText));
            }
            return element.isDisplayed() && element.getText().contains("ACCOUNT DELETED!");
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getAccountDeletedMessage() {
        try {
            WebElement element = null;
            try {
                element = wait.until(ExpectedConditions.visibilityOf(accountDeletedHeader));
            } catch (Exception e) {
                element = wait.until(ExpectedConditions.visibilityOf(accountDeletedText));
            }
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }
}