package PomClassUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class HamburgerPom {
    WebDriver driver;

    public HamburgerPom(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (id = "react-burger-menu-btn")
    private WebElement HamButton;

    @FindBy(className = "bm-menu")
    private WebElement menu;

    @FindBy (id = "inventory_sidebar_link")
    private WebElement Allitems;

    @FindBy (id = "about_sidebar_link")
    private WebElement about;

    @FindBy (id = "logout_sidebar_link")
    private WebElement logout;

    @FindBy (id = "reset_sidebar_link")
    private WebElement ResetApp;

    //Getters
    private boolean isElementDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    public void getHam()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(HamButton)).click();
    }
    public void closeMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By closeButton = By.id("react-burger-cross-btn");
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }
    public void getAllitems()
    {
        Allitems.click();
    }
    public void getAbout()
    {
        about.click();
    }
    public void getLogout()
    {
        logout.click();
    }
    public void getResetApp()
    {
        ResetApp.click();
    }
    public boolean isHamButtondisplayed()
    {
        return isElementDisplayed(HamButton);
    }
    public boolean isAllItemsdisplayed()
    {
        return isElementDisplayed(Allitems);
    }
    public boolean isAboutdisplayed()
    {
        return isElementDisplayed(about);    
    }
    public boolean isLogoutdisplayed()
    {
        return isElementDisplayed(logout);
    }
    public boolean isResetAppdisplayed()
    {
        return isElementDisplayed(ResetApp);
    }
}
