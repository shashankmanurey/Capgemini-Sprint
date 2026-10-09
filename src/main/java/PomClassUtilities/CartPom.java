package PomClassUtilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPom {
    WebDriver driver;
    public CartPom(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "remove-sauce-labs-backpack")
    private WebElement RemoveBtn;

    @FindBy (id = "checkout")
    private WebElement CheckoutBtn;

    @FindBy (id ="continue-shopping")
    private WebElement ContinueBtn;

    public void getRemove()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(RemoveBtn)).click();
    }
    public void getCheckOut()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(CheckoutBtn)).click();
    }
    public void getContinueBtn()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ContinueBtn)).click();
    }
    
    public boolean isProductDisplayed() 
    {
        return driver.findElements(By.xpath("//div[@data-test='inventory-item']")).size() > 0;
    }

    public boolean isCheckoutDisplayed() 
    {
        return CheckoutBtn.isDisplayed();
    }

    public boolean isContinueShoppingDisplayed() 
    {
        return ContinueBtn.isDisplayed();
    }

    public boolean isRemoveDisplayed() 
    {
        return RemoveBtn.isDisplayed();
    }

    public boolean isCartEmpty() 
    {
        return driver.findElements(By.xpath("//div[@data-test='inventory-item']")).isEmpty();
    }
}
