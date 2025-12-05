package Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Existing locators
    private By newUserSignupText = By.xpath("//h2[contains(text(),'New User Signup!')]");
    private By nameInput = By.name("name");
    private By emailInput = By.xpath("//input[@data-qa='signup-email']");
    private By signupBtn = By.xpath("//button[contains(text(),'Signup')]");

    // 🔹 New locators for account creation
    private By passwordInput = By.id("password");
    private By dayDropdown = By.id("days");
    private By monthDropdown = By.id("months");
    private By yearDropdown = By.id("years");
    private By firstNameInput = By.id("first_name");
    private By lastNameInput = By.id("last_name");
    private By addressInput = By.id("address1");
    private By countryDropdown = By.id("country");
    private By stateInput = By.id("state");
    private By cityInput = By.id("city");
    private By zipcodeInput = By.id("zipcode");
    private By mobileNumberInput = By.id("mobile_number");
    private By createAccountBtn = By.xpath("//button[contains(text(),'Create Account')]");

    private By accountCreatedMsg = By.xpath("//b[contains(text(),'Account Created!')]");
    private By continueBtn = By.xpath("//a[contains(text(),'Continue')]");
    private By loggedInAsText = By.xpath("//a[contains(text(),'Logged in as')]");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Existing methods
    public boolean isNewUserTextVisible() {
        return driver.findElement(newUserSignupText).isDisplayed();
    }

    public void enterNameAndEmail(String name, String email) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
    }

    public void clickSignup() {
        driver.findElement(signupBtn).click();
    }

    // 🔹 New methods for account creation
    public void fillAccountDetails(String password, String day, String month, String year,
                                   String firstName, String lastName, String address,
                                   String country, String state, String city,
                                   String zipcode, String mobile) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        driver.findElement(dayDropdown).sendKeys(day);
        driver.findElement(monthDropdown).sendKeys(month);
        driver.findElement(yearDropdown).sendKeys(year);

        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(countryDropdown).sendKeys(country);
        driver.findElement(stateInput).sendKeys(state);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(zipcodeInput).sendKeys(zipcode);
        driver.findElement(mobileNumberInput).sendKeys(mobile);

        driver.findElement(createAccountBtn).click();
    }

    public boolean isAccountCreatedVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedMsg));
        return driver.findElement(accountCreatedMsg).isDisplayed();
    }

    public void clickContinueAfterAccountCreated() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public boolean isLoggedInAsVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAsText));
        return driver.findElement(loggedInAsText).isDisplayed();
    }
}
