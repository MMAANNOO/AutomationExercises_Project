package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.By;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ---------------- LOGIN FORM ----------------
    @FindBy(css = "input[data-qa='login-email']")
    WebElement loginEmailInput;

    @FindBy(css = "input[data-qa='login-password']")
    WebElement loginPasswordInput;

    @FindBy(css = "button[data-qa='login-button']")
    WebElement loginButton;

    @FindBy(xpath = "//h2[text()='Login to your account']")
    WebElement loginToYourAccountHeader;

    // ---------------- SIGNUP FORM ----------------
    @FindBy(css = "input[data-qa='signup-name']")
    WebElement signupNameInput;

    @FindBy(css = "input[data-qa='signup-email']")
    WebElement signupEmailInput;

    @FindBy(css = "button[data-qa='signup-button']")
    WebElement signupButton;

    @FindBy(css = ".signup-form h2")
    WebElement newUserSignupHeader;

    // ---------------- ACCOUNT CREATION FORM ----------------
    @FindBy(css = "h2[data-qa='account-info']")
    WebElement enterAccountInfoHeader;

    @FindBy(css = "input[data-qa='title-mr']")
    WebElement titleMrRadio;

    @FindBy(css = "input[data-qa='title-mrs']")
    WebElement titleMrsRadio;

    @FindBy(css = "input[data-qa='password']")
    WebElement accountPasswordInput;

    @FindBy(css = "select[data-qa='days']")
    WebElement dayDropdown;

    @FindBy(css = "select[data-qa='months']")
    WebElement monthDropdown;

    @FindBy(css = "select[data-qa='years']")
    WebElement yearDropdown;

    @FindBy(css = "input[data-qa='newsletter']")
    WebElement newsletterCheckbox;

    @FindBy(css = "input[data-qa='optin']")
    WebElement specialOffersCheckbox;

    @FindBy(css = "input[data-qa='first_name']")
    WebElement firstNameInput;

    @FindBy(css = "input[data-qa='last_name']")
    WebElement lastNameInput;

    @FindBy(css = "input[data-qa='company']")
    WebElement companyInput;

    @FindBy(css = "input[data-qa='address']")
    WebElement addressInput;

    @FindBy(css = "input[data-qa='address2']")
    WebElement address2Input;

    @FindBy(css = "select[data-qa='country']")
    WebElement countryDropdown;

    @FindBy(css = "input[data-qa='state']")
    WebElement stateInput;

    @FindBy(css = "input[data-qa='city']")
    WebElement cityInput;

    @FindBy(css = "input[data-qa='zipcode']")
    WebElement zipcodeInput;

    @FindBy(css = "input[data-qa='mobile_number']")
    WebElement mobileNumberInput;

    @FindBy(css = "button[data-qa='create-account']")
    WebElement createAccountButton;

    @FindBy(css = "h2[data-qa='account-created']")
    WebElement accountCreatedHeader;

    @FindBy(css = "a[data-qa='continue-button']")
    WebElement continueButton;
    
    // Error message element for incorrect login
    @FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
    WebElement loginErrorMessage;
    
    // ---------------- SIGNUP ERROR MESSAGE ----------------
    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    WebElement signupErrorMessage;

    // ---------------- METHODS ----------------

    // LOGIN FLOW
    public boolean isLoginToYourAccountVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginToYourAccountHeader));
            return loginToYourAccountHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLoginEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(loginEmailInput));
        loginEmailInput.clear();
        loginEmailInput.sendKeys(email);
    }

    public void enterLoginPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(loginPasswordInput));
        loginPasswordInput.clear();
        loginPasswordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public void loginUser(String email, String password) {
        enterLoginEmail(email);
        enterLoginPassword(password);
        clickLoginButton();
    }

    // SIGNUP FLOW
    public boolean isNewUserSignupVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(newUserSignupHeader));
            return newUserSignupHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterSignupName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(signupNameInput));
        signupNameInput.clear();
        signupNameInput.sendKeys(name);
    }

    public void enterSignupEmail(String email) {
        signupEmailInput.clear();
        signupEmailInput.sendKeys(email);
    }

    public void clickSignupButton() {
        signupButton.click();
    }

    public void signupUser(String name, String email) {
        enterSignupName(name);
        enterSignupEmail(email);
        clickSignupButton();
    }

    // ACCOUNT CREATION FLOW
    public boolean isEnterAccountInfoVisible() {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(), 'ENTER ACCOUNT INFORMATION')]")));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectTitle(String title) {
        if (title.equalsIgnoreCase("Mr")) {
            titleMrRadio.click();
        } else if (title.equalsIgnoreCase("Mrs")) {
            titleMrsRadio.click();
        }
    }

    public void enterAccountPassword(String password) {
        accountPasswordInput.clear();
        accountPasswordInput.sendKeys(password);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        new Select(dayDropdown).selectByValue(day);
        new Select(monthDropdown).selectByValue(month);
        new Select(yearDropdown).selectByValue(year);
    }

    public void selectNewsletterCheckbox() {
        if (!newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        }
    }

    public void selectSpecialOffersCheckbox() {
        if (!specialOffersCheckbox.isSelected()) {
            specialOffersCheckbox.click();
        }
    }

    public void enterFirstName(String firstName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void enterCompany(String company) {
        companyInput.clear();
        companyInput.sendKeys(company);
    }

    public void enterAddress(String address) {
        addressInput.clear();
        addressInput.sendKeys(address);
    }

    public void enterAddress2(String address2) {
        address2Input.clear();
        address2Input.sendKeys(address2);
    }

    public void selectCountry(String country) {
        new Select(countryDropdown).selectByVisibleText(country);
    }

    public void enterState(String state) {
        stateInput.clear();
        stateInput.sendKeys(state);
    }

    public void enterCity(String city) {
        cityInput.clear();
        cityInput.sendKeys(city);
    }

    public void enterZipcode(String zipcode) {
        zipcodeInput.clear();
        zipcodeInput.sendKeys(zipcode);
    }

    public void enterMobileNumber(String mobileNumber) {
        mobileNumberInput.clear();
        mobileNumberInput.sendKeys(mobileNumber);
    }

    public void clickCreateAccountButton() {
        createAccountButton.click();
    }

    public boolean isAccountCreatedVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(accountCreatedHeader));
            return accountCreatedHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }
    
    public boolean isErrorMessageVisible() {
       try {
           wait.until(ExpectedConditions.visibilityOf(loginErrorMessage));
           return loginErrorMessage.isDisplayed() && 
                  loginErrorMessage.getText().equals("Your email or password is incorrect!");
       } catch (Exception e) {
           return false;
       }
   }
    
    public String getErrorMessage() {
       try {
           wait.until(ExpectedConditions.visibilityOf(loginErrorMessage));
           return loginErrorMessage.getText();
       } catch (Exception e) {
           return "";
       }
   }
    
    public boolean isSignupErrorMessageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(signupErrorMessage));
            return signupErrorMessage.isDisplayed() && 
                   signupErrorMessage.getText().equals("Email Address already exist!");
        }
        catch (Exception e) {
        return false;
        }
    }
}
