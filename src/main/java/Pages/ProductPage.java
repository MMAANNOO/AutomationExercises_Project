package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;


public class ProductPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;
    
    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    
    // Page Header and Navigation
    @FindBy(xpath = "//h2[@class='title text-center' and contains(text(),'All Products')]")
    private WebElement allProductsHeader;

    // Products Section
    @FindBy(css = ".features_items")
    private WebElement productsContainer;
    
    @FindBy(css = ".single-products")
    private List<WebElement> allProductCards;
    
    // First Product Elements
    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4'][1]")
    private WebElement firstProductCard;
    
    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4'][1]//a[contains(@href,'product_details') and contains(text(),'View Product')]")
    private WebElement firstProductViewButton;
    
    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4'][1]//img")
    private WebElement firstProductImage;
    
    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4'][1]//p[1]")
    private WebElement firstProductName;
    
    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4'][1]//h2")
    private WebElement firstProductPrice;
    
    // Second Product Elements
    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4'][2]")
    private WebElement secondProductCard;

    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4'][2]//p[1]")
    private WebElement secondProductName;

    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4'][2]//h2")
    private WebElement secondProductPrice;
    
    // Add to Cart buttons - using CSS selector for better performance
    @FindBy(css = ".add-to-cart")
    private List<WebElement> addToCartButtons;
    
    // Modal Elements (for add to cart functionality)
    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement addToCartModal;
    
    // Continue Shopping button (appears after adding to cart)
    @FindBy(xpath = "//button[contains(text(),'Continue Shopping')]")
    private WebElement continueShoppingButton;
    
    // View Cart button
    @FindBy(xpath = "//u[contains(text(),'View Cart')]")
    private WebElement viewCartButton;
    
    // Search Elements
    @FindBy(xpath = "//*[@id=\"search_product\"]")
    private WebElement productSearchInput;

    @FindBy(xpath = "//*[@id=\"submit_search\"]")
    private WebElement productSearchButton;
    
    private By womenDressPage = By.xpath("/html/body/section/div/div[2]/div[2]/div/h2");
    private By menTshirtsPage = By.xpath("/html/body/section/div/div[2]/div[2]/div/h2");
    private By menProductsButton = By.xpath("//*[@id=\"accordian\"]/div[2]/div[1]/h4/a");
    private By tshirtsMenProductsButton = By.xpath("//*[@id=\"Men\"]/div/ul/li[1]/a");
    private By brandsName = By.xpath("/html/body/section[2]/div/div/div[1]/div/div[3]/h2");
    private By poloBrandButton = By.xpath("/html/body/section[2]/div/div/div[1]/div/div[3]/div/ul/li[1]/a");
    private By poloBrandPage = By.xpath("/html/body/section/div/div[2]/div[2]/div/h2");
    private By madameBrandButton = By.xpath("/html/body/section/div/div[2]/div[1]/div[1]/div[2]/div/ul/li[3]/a");
    private By madameBrandPage = By.xpath("/html/body/section/div/div[2]/div[2]/div/h2");
    private By searchedProductPage = By.xpath("/html/body/section[2]/div/div/div[2]/div/h2");
    private By viewProductButton = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a");
    
    // Page Verification Methods
    public boolean isAllProductsPageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(allProductsHeader));
            String headerText = allProductsHeader.getText().trim();
            return allProductsHeader.isDisplayed() && 
                   (headerText.equals("ALL PRODUCTS") || headerText.contains("ALL PRODUCTS"));
        } catch (Exception e) {
            System.out.println("All Products header not found: " + e.getMessage());
            return false;
        }
    }
    
    public boolean isProductsListVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productsContainer));
            wait.until(ExpectedConditions.visibilityOfAllElements(allProductCards));
            return productsContainer.isDisplayed() && 
                   allProductCards.size() > 0 &&
                   allProductCards.get(0).isDisplayed();
        } catch (Exception e) {
            System.out.println("Products list not visible: " + e.getMessage());
            return false;
        }
    }
    
    // Product View Methods
    public void clickViewProductOfFirstProduct() {
        try {
            wait.until(ExpectedConditions.visibilityOf(firstProductCard));
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", firstProductCard);
            Thread.sleep(1000);
            
            wait.until(ExpectedConditions.elementToBeClickable(firstProductViewButton));
            
            try {
                firstProductViewButton.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", firstProductViewButton);
            }
            
            System.out.println("Clicked on View Product of first product");
            
        } catch (Exception e) {
            System.out.println("Error clicking View Product button: " + e.getMessage());
            throw new RuntimeException("Failed to click on View Product button", e);
        }
    }
    
    public boolean isFirstProductImageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(firstProductImage));
            return firstProductImage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isFirstProductViewButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(firstProductViewButton));
            return firstProductViewButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Enhanced Add to Cart Methods with Hover Support
    public void hoverOverFirstProduct() {
        try {
            wait.until(ExpectedConditions.visibilityOf(firstProductCard));
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", firstProductCard);
            Thread.sleep(1000);
            actions.moveToElement(firstProductCard).perform();
            Thread.sleep(500); // Allow hover effect to trigger
            System.out.println("Hovered over first product");
        } catch (Exception e) {
            System.out.println("Error hovering over first product: " + e.getMessage());
            throw new RuntimeException("Failed to hover over first product", e);
        }
    }
    
    public void addFirstProductToCart() {
    try {
        // Wait for products to be visible first
        wait.until(ExpectedConditions.visibilityOf(firstProductCard));
        
        // Scroll to first product
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", firstProductCard);
        Thread.sleep(1000);
        
        // Try multiple locator strategies for the first product's add to cart button
        WebElement firstAddToCartButton = null;
        
        // Strategy 1: Use the existing list approach
        try {
            if (addToCartButtons.size() > 0) {
                firstAddToCartButton = addToCartButtons.get(0);
                System.out.println("Found first add to cart button using list approach");
            }
        } catch (Exception e) {
            System.out.println("List approach failed: " + e.getMessage());
        }
        
        // Strategy 2: Direct xpath for first product
        if (firstAddToCartButton == null) {
            try {
                firstAddToCartButton = driver.findElement(By.xpath("//div[@class='features_items']//div[@class='col-sm-4'][1]//a[contains(@class,'add-to-cart')]"));
                System.out.println("Found first add to cart button using direct xpath");
            } catch (Exception e) {
                System.out.println("Direct xpath approach failed: " + e.getMessage());
            }
        }
        
        // Strategy 3: Alternative xpath
        if (firstAddToCartButton == null) {
            try {
                firstAddToCartButton = driver.findElement(By.xpath("(//a[contains(@class,'add-to-cart')])[1]"));
                System.out.println("Found first add to cart button using alternative xpath");
            } catch (Exception e) {
                System.out.println("Alternative xpath approach failed: " + e.getMessage());
            }
        }
        
        // Strategy 4: Try to find by text
        if (firstAddToCartButton == null) {
            try {
                firstAddToCartButton = driver.findElement(By.xpath("//div[@class='features_items']//div[@class='col-sm-4'][1]//a[contains(text(),'Add to cart')]"));
                System.out.println("Found first add to cart button using text approach");
            } catch (Exception e) {
                System.out.println("Text approach failed: " + e.getMessage());
            }
        }
        
        if (firstAddToCartButton != null) {
            // Make sure button is visible and clickable
            wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton));
            
            // Try clicking
            try {
                firstAddToCartButton.click();
                System.out.println("Successfully clicked first add to cart button");
            } catch (Exception e) {
                System.out.println("Normal click failed, trying JavaScript click");
                js.executeScript("arguments[0].click();", firstAddToCartButton);
                System.out.println("Successfully clicked first add to cart button using JavaScript");
            }
            
            // Wait for modal to appear (if it exists)
            try {
                Thread.sleep(2000); // Give time for any modal to appear
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
        } else {
            System.out.println("ERROR: Could not find first product's add to cart button with any strategy");
            throw new RuntimeException("Failed to find first product's add to cart button");
        }
        
    } catch (Exception e) {
        System.out.println("Error adding first product to cart: " + e.getMessage());
        e.printStackTrace();
        throw new RuntimeException("Failed to add first product to cart", e);
    }
}
    
    public void hoverOverSecondProduct() {
        try {
            wait.until(ExpectedConditions.visibilityOf(secondProductCard));
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", secondProductCard);
            Thread.sleep(1000);
            actions.moveToElement(secondProductCard).perform();
            Thread.sleep(500); // Allow hover effect to trigger
            System.out.println("Hovered over second product");
        } catch (Exception e) {
            System.out.println("Error hovering over second product: " + e.getMessage());
            throw new RuntimeException("Failed to hover over second product", e);
        }
    }
    
    // Replace your existing addSecondProductToCart() method with this improved version:

public void addSecondProductToCart() {
    try {
        System.out.println("Starting to add second product to cart...");
        
        // Wait a bit for any modals to disappear and page to stabilize
        Thread.sleep(2000);
        
        // Re-locate all add-to-cart buttons (page might have refreshed after first add)
        List<WebElement> currentAddToCartButtons = driver.findElements(By.cssSelector(".add-to-cart"));
        System.out.println("Found " + currentAddToCartButtons.size() + " add-to-cart buttons after continue shopping");
        
        // Wait for second product to be visible
        wait.until(ExpectedConditions.visibilityOf(secondProductCard));
        
        // Scroll to second product
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", secondProductCard);
        Thread.sleep(1000);
        
        // Try multiple locator strategies for the second product's add to cart button
        WebElement secondAddToCartButton = null;
        
        // Strategy 1: Re-fetch the list and use index 1
        try {
            List<WebElement> freshAddToCartButtons = driver.findElements(By.cssSelector(".add-to-cart"));
            if (freshAddToCartButtons.size() > 1) {
                secondAddToCartButton = freshAddToCartButtons.get(1);
                System.out.println("Found second add to cart button using fresh list approach");
            }
        } catch (Exception e) {
            System.out.println("Fresh list approach failed for second product: " + e.getMessage());
        }
        
        // Strategy 2: Direct xpath within second product card
        if (secondAddToCartButton == null) {
            try {
                secondAddToCartButton = secondProductCard.findElement(By.xpath(".//a[contains(@class,'add-to-cart')]"));
                System.out.println("Found second add to cart button using card-relative xpath");
            } catch (Exception e) {
                System.out.println("Card-relative xpath approach failed: " + e.getMessage());
            }
        }
        
        // Strategy 3: Direct xpath for second product (1-based indexing)
        if (secondAddToCartButton == null) {
            try {
                secondAddToCartButton = driver.findElement(By.xpath("//div[@class='features_items']//div[@class='col-sm-4'][2]//a[contains(@class,'add-to-cart')]"));
                System.out.println("Found second add to cart button using direct xpath");
            } catch (Exception e) {
                System.out.println("Direct xpath approach failed for second product: " + e.getMessage());
            }
        }
        
        // Strategy 4: Alternative xpath using position
        if (secondAddToCartButton == null) {
            try {
                secondAddToCartButton = driver.findElement(By.xpath("(//a[contains(@class,'add-to-cart')])[2]"));
                System.out.println("Found second add to cart button using position xpath");
            } catch (Exception e) {
                System.out.println("Position xpath approach failed for second product: " + e.getMessage());
            }
        }
        
        // Strategy 5: Try to find by text within second product
        if (secondAddToCartButton == null) {
            try {
                secondAddToCartButton = secondProductCard.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));
                System.out.println("Found second add to cart button using text within card");
            } catch (Exception e) {
                System.out.println("Text within card approach failed: " + e.getMessage());
            }
        }
        
        // Strategy 6: Find all add-to-cart buttons and filter by the one within second product area
        if (secondAddToCartButton == null) {
            try {
                List<WebElement> allButtons = driver.findElements(By.xpath("//a[contains(@class,'add-to-cart')]"));
                for (int i = 0; i < allButtons.size(); i++) {
                    WebElement button = allButtons.get(i);
                    // Check if this button is within the second product area
                    try {
                        String buttonLocation = button.getLocation().toString();
                        System.out.println("Button " + (i+1) + " location: " + buttonLocation);
                        // Use the second button found
                        if (i == 1) {
                            secondAddToCartButton = button;
                            System.out.println("Using second button from all buttons list");
                            break;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            } catch (Exception e) {
                System.out.println("All buttons iteration failed: " + e.getMessage());
            }
        }
        
        if (secondAddToCartButton != null) {
            // Hover over second product first
            try {
                actions.moveToElement(secondProductCard).perform();
                Thread.sleep(1000);
                System.out.println("Hovered over second product card");
            } catch (Exception e) {
                System.out.println("Hover failed, continuing without hover: " + e.getMessage());
            }
            
            // Make sure button is visible and clickable
            try {
                wait.until(ExpectedConditions.elementToBeClickable(secondAddToCartButton));
            } catch (Exception e) {
                System.out.println("Wait for clickable failed, but continuing: " + e.getMessage());
            }
            
            // Try clicking
            try {
                secondAddToCartButton.click();
                System.out.println("Successfully clicked second add to cart button");
            } catch (Exception e) {
                System.out.println("Normal click failed, trying JavaScript click");
                try {
                    js.executeScript("arguments[0].click();", secondAddToCartButton);
                    System.out.println("Successfully clicked second add to cart button using JavaScript");
                } catch (Exception jsE) {
                    System.out.println("JavaScript click also failed: " + jsE.getMessage());
                    throw jsE;
                }
            }
            
            // Wait for modal to appear (if it exists)
            try {
                Thread.sleep(2000); // Give time for any modal to appear
                System.out.println("Waited for modal after second product add");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
        } else {
            System.out.println("ERROR: Could not find second product's add to cart button with any strategy");
            
            // Debug information
            System.out.println("=== DEBUG INFO FOR SECOND PRODUCT ===");
            System.out.println("Second product card visible: " + secondProductCard.isDisplayed());
            List<WebElement> debugButtons = driver.findElements(By.xpath("//a[contains(@class,'add-to-cart') or contains(text(),'Add to cart')]"));
            System.out.println("Total add-to-cart buttons found on page: " + debugButtons.size());
            for (int i = 0; i < debugButtons.size(); i++) {
                try {
                    System.out.println("Button " + (i+1) + ": " + debugButtons.get(i).getText() + " - Displayed: " + debugButtons.get(i).isDisplayed());
                } catch (Exception e) {
                    System.out.println("Button " + (i+1) + ": Error getting info - " + e.getMessage());
                }
            }
            System.out.println("=====================================");
            
            throw new RuntimeException("Failed to find second product's add to cart button");
        }
        
    } catch (Exception e) {
        System.out.println("Error adding second product to cart: " + e.getMessage());
        e.printStackTrace();
        throw new RuntimeException("Failed to add second product to cart", e);
    }
}
    
    // Modal and Cart Navigation Methods
    public void clickContinueShopping() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
            continueShoppingButton.click();
            
            // Wait for modal to disappear (if it exists)
            try {
                wait.until(ExpectedConditions.invisibilityOf(addToCartModal));
            } catch (Exception e) {
                // Modal might not exist, continue
            }
            
            System.out.println("Clicked Continue Shopping");
        } catch (Exception e) {
            System.out.println("Error clicking Continue Shopping: " + e.getMessage());
            throw new RuntimeException("Failed to click Continue Shopping", e);
        }
    }
    
    public void clickViewCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
            viewCartButton.click();
            System.out.println("Clicked View Cart");
        } catch (Exception e) {
            System.out.println("Error clicking View Cart: " + e.getMessage());
            throw new RuntimeException("Failed to click View Cart", e);
        }
    }
    
    public CartPage clickViewCartButton() {
        clickViewCart();
        return new CartPage(driver);
    }
    
    // Search Functionality
    public void enterProductName(String name) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(productSearchInput));
            productSearchInput.clear();
            productSearchInput.sendKeys(name);
            System.out.println("Entered product name: " + name);
        } catch (Exception e) {
            System.out.println("Error entering product name: " + e.getMessage());
        }
    }
    
    public void clickProductSearchButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(productSearchButton));
            productSearchButton.click();
            System.out.println("Clicked product search button");
        } catch (Exception e) {
            System.out.println("Error clicking product search button: " + e.getMessage());
        }
    }
    
    // Product Information Getters
    public String getFirstProductName() {
        try {
            wait.until(ExpectedConditions.visibilityOf(firstProductName));
            return firstProductName.getText();
        } catch (Exception e) {
            System.out.println("Error getting first product name: " + e.getMessage());
            return "";
        }
    }
    
    public String getFirstProductPrice() {
        try {
            wait.until(ExpectedConditions.visibilityOf(firstProductPrice));
            return firstProductPrice.getText();
        } catch (Exception e) {
            System.out.println("Error getting first product price: " + e.getMessage());
            return "";
        }
    }
    
    public String getSecondProductName() {
        try {
            wait.until(ExpectedConditions.visibilityOf(secondProductName));
            return secondProductName.getText();
        } catch (Exception e) {
            System.out.println("Error getting second product name: " + e.getMessage());
            return "";
        }
    }

    public String getSecondProductPrice() {
        try {
            wait.until(ExpectedConditions.visibilityOf(secondProductPrice));
            return secondProductPrice.getText();
        } catch (Exception e) {
            System.out.println("Error getting second product price: " + e.getMessage());
            return "";
        }
    }
    
    // General Page Information Methods
    public int getProductsCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(allProductCards));
            return allProductCards.size();
        } catch (Exception e) {
            System.out.println("Error getting products count: " + e.getMessage());
            return 0;
        }
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public String getAllProductsHeaderText() {
        try {
            wait.until(ExpectedConditions.visibilityOf(allProductsHeader));
            return allProductsHeader.getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    // Utility Methods
    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }
    
    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    
    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(productsContainer));
        wait.until(ExpectedConditions.visibilityOf(allProductsHeader));
    }
    
    public boolean isProductsPageVisible() {
        return isAllProductsPageVisible();
    }
    
    public void hoverAndAddFirstProductToCart() {
    hoverOverFirstProduct();
    addFirstProductToCart();
    }
    
    public void hoverAndAddSecondProductToCart() {
    hoverOverSecondProduct();  
    addSecondProductToCart();
    }
    
    public CartPage addFirstProductAndViewCart() {
        hoverOverFirstProduct();
        addFirstProductToCart();

        try {
            wait.until(ExpectedConditions.visibilityOf(addToCartModal));
            wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
            viewCartButton.click();
            System.out.println("Added first product and navigated to cart");
        } catch (Exception e) {
            System.out.println("Error clicking View Cart after adding first product: " + e.getMessage());
            throw new RuntimeException("Failed to add product and view cart", e);
        }

        return new CartPage(driver);
    }
    
     public CartPage addTwoProductsAndViewCart() {
        hoverOverFirstProduct();
        addFirstProductToCart();

        // Continue shopping after adding first
        clickContinueShopping();

        // Add second product
        hoverOverSecondProduct();
        addSecondProductToCart();

        // View Cart after second add
        clickViewCart();

        return new CartPage(driver);
    }
     
    // Debug Methods
    public void printProductsInfo() {
        System.out.println("=== PRODUCTS PAGE INFO ===");
        System.out.println("Page Title: " + getPageTitle());
        System.out.println("Current URL: " + getCurrentUrl());
        System.out.println("Header Text: " + getAllProductsHeaderText());
        System.out.println("Total Products: " + getProductsCount());
        System.out.println("First Product Name: " + getFirstProductName());
        System.out.println("First Product Price: " + getFirstProductPrice());
        System.out.println("Second Product Name: " + getSecondProductName());
        System.out.println("Second Product Price: " + getSecondProductPrice());
        System.out.println("===========================");
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
     
    public boolean isBrandsVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(brandsName));
            return driver.findElement(brandsName).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickPoloBrandButton() {
        wait.until(ExpectedConditions.elementToBeClickable(poloBrandButton)).click();
    }
    
    public boolean isPoloBrandPageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(poloBrandPage));
            return driver.findElement(poloBrandPage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickMadameBrandButton() {
        wait.until(ExpectedConditions.elementToBeClickable(madameBrandButton)).click();
    }
    
    public boolean isMadameBrandPageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(madameBrandPage));
            return driver.findElement(poloBrandPage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSearchedProductPageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchedProductPage));
            return driver.findElement(searchedProductPage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickViewProductButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewProductButton)).click();
    }
}