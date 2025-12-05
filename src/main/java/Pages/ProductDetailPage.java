package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ProductDetailPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Locators (using relative XPath instead of absolute)
    @FindBy(xpath = "//div[@class='product-information']/h2")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='product-information']/p[1]")
    private WebElement productCategory;

    @FindBy(xpath = "//div[@class='product-information']//span/span")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='product-information']/p[2]")
    private WebElement productAvailability;

    @FindBy(xpath = "//div[@class='product-information']/p[3]")
    private WebElement productCondition;

    @FindBy(xpath = "//div[@class='product-information']/p[4]")
    private WebElement productBrand;

    @FindBy(xpath = "//div[@class='product-information']")
    private WebElement productDetailsSection;

    @FindBy(xpath = "//input[@id='quantity']")
    private WebElement quantityInput;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/button")
    private WebElement addToCartButton;
    
    @FindBy(xpath = "//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a/u")
    private WebElement viewCartbutton;
    
    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement inputName;
    
    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement inputEmail;
    
    @FindBy(xpath = "//*[@id=\"review\"]")
    private WebElement inputReview;
    
    @FindBy(xpath = "//*[@id=\"button-review\"]")
    private WebElement submitButton;
       
    @FindBy(xpath = "//*[@id=\"review-section\"]/div/div/span")
    private WebElement submitMessage;

    // ✅ Page validations
    public boolean isProductDetailPageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productDetailsSection));
            return productDetailsSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductNameVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productName));
            return productName.isDisplayed() && !productName.getText().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductCategoryVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productCategory));
            return productCategory.isDisplayed() && productCategory.getText().contains("Category:");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductPriceVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productPrice));
            return productPrice.isDisplayed() && !productPrice.getText().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductAvailabilityVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productAvailability));
            return productAvailability.isDisplayed() && productAvailability.getText().contains("Availability:");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductConditionVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productCondition));
            return productCondition.isDisplayed() && productCondition.getText().contains("Condition:");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductBrandVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productBrand));
            return productBrand.isDisplayed() && productBrand.getText().contains("Brand:");
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Quantity controls
    public void setQuantity(String quantity) {
        wait.until(ExpectedConditions.elementToBeClickable(quantityInput));
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
    }

    public void setQuantity(int quantity) {
        setQuantity(String.valueOf(quantity));
    }

    public String getQuantity() {
        wait.until(ExpectedConditions.visibilityOf(quantityInput));
        return quantityInput.getAttribute("value");
    }

    // ✅ Add to cart
    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    // ✅ Getters with waits
    public String getProductName() {
        wait.until(ExpectedConditions.visibilityOf(productName));
        return productName.getText();
    }

    public String getProductCategory() {
        wait.until(ExpectedConditions.visibilityOf(productCategory));
        return productCategory.getText();
    }

    public String getProductPrice() {
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        return productPrice.getText();
    }

    public String getProductAvailability() {
        wait.until(ExpectedConditions.visibilityOf(productAvailability));
        return productAvailability.getText();
    }

    public String getProductCondition() {
        wait.until(ExpectedConditions.visibilityOf(productCondition));
        return productCondition.getText();
    }

    public String getProductBrand() {
        wait.until(ExpectedConditions.visibilityOf(productBrand));
        return productBrand.getText();
    }

    public boolean areAllProductDetailsVisible() {
        return isProductNameVisible() &&
               isProductCategoryVisible() &&
               isProductPriceVisible() &&
               isProductAvailabilityVisible() &&
               isProductConditionVisible() &&
               isProductBrandVisible();
    }

    // ✅ Page metadata
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    // ✅ New: Get all product details together
    public String getAllProductDetails() {
        return String.format(
            "Name: %s | Category: %s | Price: %s | Availability: %s | Condition: %s | Brand: %s",
            getProductName(),
            getProductCategory(),
            getProductPrice(),
            getProductAvailability(),
            getProductCondition(),
            getProductBrand()
        );
    }
    
    public void clickviewCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(viewCartbutton));
            viewCartbutton.click();
            System.out.println("Clicked View Cart");
        } catch (Exception e) {
            System.out.println("Error clicking View Cart: " + e.getMessage());
            throw new RuntimeException("Failed to click View Cart", e);
        }
    }

    // ✅ New: Validate that product details are consistent (not empty/placeholder)
    public boolean areProductDetailsConsistent() {
        return !getProductName().trim().isEmpty() &&
               getProductCategory().contains("Category:") &&
               getProductPrice().matches(".*\\d+.*") &&
               getProductAvailability().contains("Availability:") &&
               getProductCondition().contains("Condition:") &&
               getProductBrand().contains("Brand:");
    }
    
    public void enterYourName(String name) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(inputName));
            inputName.clear();
            inputName.sendKeys(name);
            System.out.println("Entered name: " + name);
        } catch (Exception e) {
            System.out.println("Error name: " + e.getMessage());
        }
    }
    
    public void enterYourEmail(String email) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(inputEmail));
            inputEmail.clear();
            inputEmail.sendKeys(email);
            System.out.println("Entered email: " + email);
        } catch (Exception e) {
            System.out.println("Error email: " + e.getMessage());
        }
    }
    
    public void enterYourReview(String review) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(inputReview));
            inputReview.clear();
            inputReview.sendKeys(review);
            System.out.println("Entered review: " + review);
        } catch (Exception e) {
            System.out.println("Error review: " + e.getMessage());
        }
    }
    
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
    
    public boolean isSubmitMessageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(submitMessage));
            return submitMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
