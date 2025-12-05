package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class TestCasePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    public TestCasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isTestCasesPageLoaded() {
        try {
            // Wait for the main content to be present
            wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("//div[@class='col-sm-9 col-sm-offset-1']")
            ));
            
            // Check if URL contains test_cases
            return driver.getCurrentUrl().toLowerCase().contains("test_cases");
            
        } catch (Exception e) {
            System.out.println("Test Cases page not loaded: " + e.getMessage());
            return false;
        }
    }
}