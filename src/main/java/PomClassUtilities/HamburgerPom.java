package PomClassUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HamburgerPom {
    WebDriver driver;

    public HamburgerPom(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (id = "react-burger-menu-btn")
    private WebElement HamButton;

    @FindBy (id = "inventory_sidebar_link")
    private WebElement Allitems;

    @FindBy (id = "about_sidebar_link")
    private WebElement about;

    @FindBy (id = "logout_sidebar_link")
    private WebElement logout;

    @FindBy (id = "reset_sidebar_link")
    private WebElement ResetApp;

    //Getters
    public void getHam()
    {
        HamButton.click();
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
}
