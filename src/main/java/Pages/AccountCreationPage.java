package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreationPage {
    private WebDriver driver;

    // Locators
    private By titleMr = By.id("id_gender1");
    private By passwordInput = By.id("password");
    private By daySelect = By.id("days");
    private By monthSelect = By.id("months");
    private By yearSelect = By.id("years");
    private By newsletterCheckbox = By.id("newsletter");
    private By offersCheckbox = By.id("optin");

    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By company = By.id("company");
    private By address1 = By.id("address1");
    private By country = By.id("country");
    private By state = By.id("state");
    private By city = By.id("city");
    private By zipcode = By.id("zipcode");
    private By mobileNumber = By.id("mobile_number");

    private By createAccountBtn = By.xpath("//button[contains(text(),'Create Account')]");

    public AccountCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Fill out registration form
    public void fillAccountDetails(String password) {
        driver.findElement(titleMr).click();
        driver.findElement(passwordInput).sendKeys(password);

        // Date of Birth
        driver.findElement(daySelect).sendKeys("30");
        driver.findElement(monthSelect).sendKeys("March");
        driver.findElement(yearSelect).sendKeys("2002");

        // Checkboxes
        driver.findElement(newsletterCheckbox).click();
        driver.findElement(offersCheckbox).click();

        // Personal info
        driver.findElement(firstName).sendKeys("Mahmoud");
        driver.findElement(lastName).sendKeys("Ahmed");
        driver.findElement(company).sendKeys("Edges");
        driver.findElement(address1).sendKeys("123 Street, Cairo");
        driver.findElement(country).sendKeys("Egypt");
        driver.findElement(state).sendKeys("Cairo");
        driver.findElement(city).sendKeys("Shobra");
        driver.findElement(zipcode).sendKeys("12345");
        driver.findElement(mobileNumber).sendKeys("0100000000");
    }

    public void clickCreateAccount() {
        driver.findElement(createAccountBtn).click();
    }
}
