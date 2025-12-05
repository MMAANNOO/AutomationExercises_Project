package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Alert;
import java.time.Duration;
import org.openqa.selenium.By;

public class ContactUsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    // Page Elements
    @FindBy(css = ".contact-form h2")
    private WebElement getInTouchHeader;
    
    @FindBy(xpath = "//h2[text()='Get In Touch']")
    private WebElement getInTouchHeaderAlternative;
    
    @FindBy(css = "input[data-qa='name']")
    private WebElement nameInput;
    
    @FindBy(css = "input[data-qa='email']")
    private WebElement emailInput;
    
    @FindBy(css = "input[data-qa='subject']")
    private WebElement subjectInput;
    
    @FindBy(css = "textarea[data-qa='message']")
    private WebElement messageTextarea;
    
    @FindBy(css = "input[name='upload_file']")
    private WebElement uploadFileInput;
    
    @FindBy(css = "input[data-qa='submit-button']")
    private WebElement submitButton;
    
    @FindBy(css = ".status.alert.alert-success")
    private WebElement successMessage;
    
    @FindBy(xpath = "//div[contains(text(), 'Success! Your details have been submitted successfully.')]")
    private WebElement successMessageAlternative;
    
    @FindBy(css = "a.btn.btn-success")
    private WebElement homeButton;
    
    @FindBy(linkText = "Home")
    private WebElement homeButtonAlternative;
    
    // Page Actions
    public boolean isGetInTouchVisible() {
        try {
            // Try primary selector first
            try {
                wait.until(ExpectedConditions.visibilityOf(getInTouchHeader));
                return getInTouchHeader.isDisplayed() && 
                       getInTouchHeader.getText().contains("GET IN TOUCH");
            } catch (Exception e) {
                // Try alternative selector
                wait.until(ExpectedConditions.visibilityOf(getInTouchHeaderAlternative));
                return getInTouchHeaderAlternative.isDisplayed();
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public void enterName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameInput));
        nameInput.clear();
        nameInput.sendKeys(name);
    }
    
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }
    
    public void enterSubject(String subject) {
        wait.until(ExpectedConditions.elementToBeClickable(subjectInput));
        subjectInput.clear();
        subjectInput.sendKeys(subject);
    }
    
    public void enterMessage(String message) {
        wait.until(ExpectedConditions.elementToBeClickable(messageTextarea));
        messageTextarea.clear();
        messageTextarea.sendKeys(message);
    }
    
    public void uploadFile(String filePath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("input[name='upload_file']")));
        uploadFileInput.sendKeys(filePath);
    }
    
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }
    
    public void handleAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept(); // Click OK button
        } catch (Exception e) {
            System.out.println("No alert present or alert handling failed");
        }
    }
    
    public boolean isSuccessMessageVisible() {
        try {
            // Try primary selector first
            try {
                wait.until(ExpectedConditions.visibilityOf(successMessage));
                return successMessage.isDisplayed() && 
                       successMessage.getText().contains("Success! Your details have been submitted successfully.");
            } catch (Exception e) {
                // Try alternative selector
                wait.until(ExpectedConditions.visibilityOf(successMessageAlternative));
                return successMessageAlternative.isDisplayed();
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getSuccessMessage() {
        try {
            try {
                wait.until(ExpectedConditions.visibilityOf(successMessage));
                return successMessage.getText();
            } catch (Exception e) {
                wait.until(ExpectedConditions.visibilityOf(successMessageAlternative));
                return successMessageAlternative.getText();
            }
        } catch (Exception e) {
            return "";
        }
    }
    
    public void clickHomeButton() {
        try {
            // Try primary selector first
            try {
                wait.until(ExpectedConditions.elementToBeClickable(homeButton));
                homeButton.click();
            } catch (Exception e) {
                // Try alternative selector
                wait.until(ExpectedConditions.elementToBeClickable(homeButtonAlternative));
                homeButtonAlternative.click();
            }
        } catch (Exception e) {
            System.out.println("Home button not found or not clickable");
        }
    }
    
    public void fillContactForm(String name, String email, String subject, String message) {
        enterName(name);
        enterEmail(email);
        enterSubject(subject);
        enterMessage(message);
    }
}