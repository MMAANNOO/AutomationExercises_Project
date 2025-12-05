package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ✅ Locators
    @FindBy(xpath = "//*[@id=\"cart_items\"]/div")
    private WebElement shoppingCartHeader;

    @FindBy(xpath = "//tr[contains(@id,'product')]")
    private List<WebElement> cartRows;

    @FindBy(xpath = "//td[@class='cart_quantity']/button")
    private List<WebElement> cartQuantities;

    @FindBy(xpath = "//td[@class='cart_description']/h4/a")
    private List<WebElement> cartProductNames;

    @FindBy(xpath = "//td[@class='cart_price']/p")
    private List<WebElement> cartProductPrices;

    @FindBy(xpath = "//td[@class='cart_total']/p")
    private List<WebElement> cartProductTotals;

    private By proceedToCheckoutBtn = By.xpath("//a[contains(text(),'Proceed To Checkout')]");
    
    private By continueOnCartBtn = By.xpath("//button[contains(text(),'Continue On Cart')]");
    
    @FindBy(xpath = "//table[@id='cart_info_table']//tr[contains(@id,'product')]")
    private List<WebElement> cartProducts;

    @FindBy(xpath = "(//table[@id='cart_info_table']//tr[contains(@id,'product')])[1]//td[@class='cart_quantity']//button")
    private WebElement firstProductQuantity;
    
    @FindBy(xpath = "(//table[@id='cart_info_table']//tr[contains(@id,'product')])[2]//td[@class='cart_quantity']//button")
    private WebElement secondProductQuantity;
    
    @FindBy(name = "name_on_card")
    private WebElement nameOnCardInput;

    @FindBy(name = "card_number")
    private WebElement cardNumberInput;

    @FindBy(name = "cvc")
    private WebElement cvcInput;

    @FindBy(name = "expiry_month")
    private WebElement expiryMonthInput;

    @FindBy(name = "expiry_year")
    private WebElement expiryYearInput;

    @FindBy(xpath = "//*[@id=\"submit\"]")
    private WebElement payAndConfirmBtn;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div/h2/b")
    private WebElement orderSuccessMessage;
    
    @FindBy(xpath = "//*[@id=\"checkoutModal\"]/div/div/div[2]/p[2]/a/u")
    private WebElement RegisterLoginBtn;
    
    @FindBy(xpath = "//*[@id=\"ordermsg\"]/textarea")
    private WebElement commentTextArea;
    
    @FindBy(xpath = "//*[@id=\"cart_items\"]/div/div[2]/h2")
    private WebElement addressDetailsSection;
    
    @FindBy(xpath = "//*[@id=\"cart_items\"]/div/div[4]/h2")
    private WebElement reviewOrderSection;
    
    @FindBy(xpath = "//*[@id=\"cart_items\"]/div/div[7]/a")
    private WebElement placeOrderBtn;
    
    @FindBy(xpath = "/html/body/section/div/div[2]/table/tbody/tr/td[6]/a")
    private WebElement removeProductbutton;
    
    @FindBy(xpath = "//*[@id=\"empty_cart\"]/p/b")
    private WebElement emptyCart;
    

    // ✅ Page validations
    public boolean isCartPageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(shoppingCartHeader));
            return shoppingCartHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getProductCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartRows));
        return cartRows.size();
    }

    // ✅ Get product details
    public String getProductName(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartProductNames));
        return cartProductNames.get(index).getText();
    }

    public String getProductPrice(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartProductPrices));
        return cartProductPrices.get(index).getText();
    }

    public String getProductTotal(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartProductTotals));
        return cartProductTotals.get(index).getText();
    }

    public int getProductQuantity(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartQuantities));
        return Integer.parseInt(cartQuantities.get(index).getText().trim());
    }

    // ✅ Verify product is displayed with exact quantity
    public boolean isProductDisplayedWithQuantity(String productName, int expectedQty) {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartProductNames));
        for (int i = 0; i < cartProductNames.size(); i++) {
            if (cartProductNames.get(i).getText().equalsIgnoreCase(productName)) {
                int actualQty = getProductQuantity(i);
                return actualQty == expectedQty;
            }
        }
        return false;
    }

    // ✅ Click proceed / continue buttons
    public void clickProceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();
    }

    public void clickContinueOnCart() {
        wait.until(ExpectedConditions.elementToBeClickable(continueOnCartBtn)).click();
    }

    // ✅ New: Get all product names in cart
    public List<String> getAllProductNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartProductNames));
        return cartProductNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    // ✅ New: Get all product quantities
    public List<Integer> getAllProductQuantities() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartQuantities));
        return cartQuantities.stream()
                .map(el -> Integer.parseInt(el.getText().trim()))
                .collect(Collectors.toList());
    }

    // ✅ New: Remove product by index
    public void removeProductByIndex(int index) {
        WebElement deleteBtn = cartRows.get(index).findElement(By.className("cart_quantity_delete"));
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
    }

    // ✅ New: Clear all products
    public void clearCart() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartRows));
        for (WebElement row : cartRows) {
            WebElement deleteBtn = row.findElement(By.className("cart_quantity_delete"));
            wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
        }
    }
    
    public boolean isFirstProductInCart() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartProducts));
        return !cartProducts.isEmpty();
    }

    public boolean verifyFirstProductQuantity(String expectedQuantity) {
        wait.until(ExpectedConditions.visibilityOf(firstProductQuantity));
        String actualQuantity = firstProductQuantity.getText().trim();
        return actualQuantity.equals(expectedQuantity);
    }
    
    public boolean isSecondProductInCart() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartProducts));
        return !cartProducts.isEmpty();
    }
    
    public boolean verifySecondProductQuantity(String expectedQuantity) {
        wait.until(ExpectedConditions.visibilityOf(secondProductQuantity));
        String actualQuantity = secondProductQuantity.getText().trim();
        return actualQuantity.equals(expectedQuantity);
    }
    

    // ✅ Metadata
    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    // Checkout flow
    public boolean isAddressDetailsVisible() {
        return wait.until(ExpectedConditions.visibilityOf(addressDetailsSection)).isDisplayed();
    }

    public boolean isReviewOrderVisible() {
        return wait.until(ExpectedConditions.visibilityOf(reviewOrderSection)).isDisplayed();
    }

    public void enterOrderComment(String comment) {
        wait.until(ExpectedConditions.visibilityOf(commentTextArea)).sendKeys(comment);
    }

    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
    }

    // Payment flow
    public void enterPaymentDetails(String name, String cardNumber, String cvc, String expMonth, String expYear) {
        wait.until(ExpectedConditions.visibilityOf(nameOnCardInput)).sendKeys(name);
        cardNumberInput.sendKeys(cardNumber);
        cvcInput.sendKeys(cvc);
        expiryMonthInput.sendKeys(expMonth);
        expiryYearInput.sendKeys(expYear);
    }

    public void clickPayAndConfirm() {
        wait.until(ExpectedConditions.elementToBeClickable(payAndConfirmBtn)).click();
    }

    public boolean isOrderSuccessMessageVisible() {
        return wait.until(ExpectedConditions.visibilityOf(orderSuccessMessage)).isDisplayed();
    }
    
    public void clickRegisterLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(RegisterLoginBtn)).click();
    }
    
    public void clickRemoveProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(removeProductbutton)).click();
    }
    
    public boolean isCartEmpty() {
        return wait.until(ExpectedConditions.visibilityOf(emptyCart)).isDisplayed();
    }

}
