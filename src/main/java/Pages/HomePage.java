package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By homePageLogo = By.xpath("//img[@alt='Website for automation practice']");
    private By loggedInAs = By.xpath("//a[contains(text(),'Logged in as')]");
    private By deleteAccountBtn = By.xpath("//a[contains(text(),'Delete Account')]");
    private By accountDeletedMsg = By.xpath("//b[contains(text(),'Account Deleted!')]");
    private By contactUsButton = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[8]/a");
    private By TestCaseButton = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a");
    private By productsButton = By.xpath("//a[contains(text(),'Products')]");
    private By featuresItemsHeader = By.xpath("//h2[text()='Features Items']");
    private By subscriptionText = By.xpath("//h2[text()='Subscription']");
    private By subscriptionEmailInput = By.id("susbscribe_email");
    private By subscriptionButton = By.id("subscribe");
    private By successMessage = By.xpath("//*[contains(text(),'You have been successfully subscribed!')]");
    private By womenProductsButton = By.xpath("//*[@id=\"accordian\"]/div[1]/div[1]/h4/a");
    private By dressWomenProductsButton = By.xpath("//*[@id=\"Women\"]/div/ul/li[1]/a");
    private By categoryName = By.xpath("/html/body/section[2]/div/div/div[1]/div/h2");
    private By womenDressPage = By.xpath("/html/body/section/div/div[2]/div[2]/div/h2");
    private By menTshirtsPage = By.xpath("/html/body/section/div/div[2]/div[2]/div/h2");
    private By menProductsButton = By.xpath("//*[@id=\"accordian\"]/div[2]/div[1]/h4/a");
    private By tshirtsMenProductsButton = By.xpath("//*[@id=\"Men\"]/div/ul/li[1]/a");
    private By recommendedItems = By.xpath("/html/body/section[2]/div/div/div[2]/div[2]/h2");    
    private By firstRecommendedItems = By.xpath("//*[@id=\"recommended-item-carousel\"]/div/div[1]/div[1]/div/div/div/a");
    private By viewCartButton = By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a/u");
    private By scrollUpArrowButton = By.xpath("//*[@id=\"scrollUp\"]");
    
    @FindBy(xpath = "//div[@class='productinfo text-center']//a[contains(text(),'View Product')]")
    private List<WebElement> viewProductButtons;

    @FindBy(xpath = "//div[@class='choose']//a[contains(text(),'View Product')]")
    private List<WebElement> viewProductLinks;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ✅ Check if home page is visible
    public boolean isHomePageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(homePageLogo));
            return driver.findElement(homePageLogo).isDisplayed() &&
                   driver.getTitle().contains("Automation Exercise") &&
                   driver.findElement(featuresItemsHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // 🔹 Click Signup/Login
    public void clickLoginSignup() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLoginBtn)).click();
    }

    // 🔹 Verify logged in as user
    public boolean isLoggedInAsUserVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAs));
        return driver.findElement(loggedInAs).isDisplayed();
    }

    // 🔹 Click delete account
    public void clickDeleteAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountBtn)).click();
    }

    // 🔹 Verify account deleted message
    public boolean isAccountDeletedVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeletedMsg));
        return driver.findElement(accountDeletedMsg).isDisplayed();
    }

    // 🔹 Navigate buttons
    public void clickContactUsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(contactUsButton)).click();
    }
    
    public boolean isTestCaseButtonVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TestCaseButton));
        return driver.findElement(TestCaseButton).isDisplayed();
    }

    public void clickTestCaseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(TestCaseButton)).click();
    }

    public void clickProductsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(productsButton)).click();
    }

    // ✅ Page metadata
    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // 🔹 Scroll to subscription section
    public void scrollToSubscription() {
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(subscriptionText));
    }

    public boolean isSubscriptionTextVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionText));
            return driver.findElement(subscriptionText).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterEmailAndSubscribe(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionEmailInput));
        driver.findElement(subscriptionEmailInput).clear();
        driver.findElement(subscriptionEmailInput).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(subscriptionButton)).click();
    }

    public boolean isSuccessMessageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Product interactions
    public void clickViewProductForFirstProduct() {
        wait.until(ExpectedConditions.visibilityOfAllElements(viewProductButtons));
        viewProductButtons.get(0).click();
    }

    public void clickViewProductForSecondProduct() {
        wait.until(ExpectedConditions.visibilityOfAllElements(viewProductButtons));
        if (viewProductButtons.size() > 1) {
            viewProductButtons.get(1).click();
        } else {
            throw new RuntimeException("Less than 2 products available on the page");
        }
    }

    public void clickViewProductByIndex(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(viewProductButtons));
        if (index < viewProductButtons.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(viewProductButtons.get(index))).click();
        } else {
            throw new RuntimeException("Product index " + index + " not found. Total products: " + viewProductButtons.size());
        }
    }

    public int getTotalProductsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(viewProductButtons));
        return viewProductButtons.size();
    }

    public void clickFirstViewProduct() {
    wait.until(ExpectedConditions.visibilityOfAllElements(viewProductLinks));
    WebElement firstProduct = viewProductLinks.get(0);

    try {
        // Scroll into view
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", firstProduct);

        // Try normal click
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    } catch (Exception e) {
        // Fallback: force JS click if intercepted
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", firstProduct);
    }
}


    // ✅ New: Check if products are loaded
    public boolean areProductsVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(viewProductButtons));
            return !viewProductButtons.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ New: Get all product names (to validate homepage items)
    public List<String> getAllProductNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(viewProductButtons));
        return viewProductButtons.stream().map(WebElement::getText).toList();
    }
    
     public void clickCartButton() {
        By cartButton = By.xpath("//a[contains(text(),'Cart')]");
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }

    /**
     * Checks if 'ACCOUNT CREATED!' message is displayed
     */
    public boolean isAccountCreatedVisible() {
        By accountCreatedMsg = By.xpath("//b[contains(text(),'Account Created!')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedMsg));
        return driver.findElement(accountCreatedMsg).isDisplayed();
    }

    /**
     * Clicks the Continue button after account creation
     */
    public void clickContinueAfterAccountCreation() {
        By continueButton = By.xpath("//a[contains(text(),'Continue')]");
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    /**
     * Verifies that 'ACCOUNT DELETED!' is displayed
     */
    public boolean isAccountDeletedMessageVisible() {
        By accountDeletedText = By.xpath("//b[contains(text(),'Account Deleted!')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeletedText));
        return driver.findElement(accountDeletedText).isDisplayed();
    }

    /**
     * Clicks Continue after account deletion
     */
    public void clickContinueAfterAccountDeletion() {
        By continueAfterDeleteBtn = By.xpath("//a[contains(text(),'Continue')]");
        wait.until(ExpectedConditions.elementToBeClickable(continueAfterDeleteBtn)).click();
    }
    
     public boolean isCategoryNameVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(categoryName));
            return driver.findElement(categoryName).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickWomenProductButton() {
        wait.until(ExpectedConditions.elementToBeClickable(womenProductsButton)).click();
    }
    
    public void clickDressWomenProductButton() {
        wait.until(ExpectedConditions.elementToBeClickable(dressWomenProductsButton)).click();
    }
    
     public boolean isWomenPageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(womenDressPage));
            return driver.findElement(womenDressPage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isMenPageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(menTshirtsPage));
            return driver.findElement(menTshirtsPage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickMenProductButton() {
        wait.until(ExpectedConditions.elementToBeClickable(menProductsButton)).click();
    }
    
    public void clickTshirtsMenProductButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tshirtsMenProductsButton)).click();
    }
    
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    public boolean isRecommendedItemsVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(recommendedItems));
        return driver.findElement(recommendedItems).isDisplayed();
    }
    
    public void clickFirstProductButton() {
        wait.until(ExpectedConditions.elementToBeClickable(firstRecommendedItems)).click();
    }
    
    public void clickViewCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
    }
    
    public void clickScrollUpArrowButton() {
        wait.until(ExpectedConditions.elementToBeClickable(scrollUpArrowButton)).click();
    }
}
