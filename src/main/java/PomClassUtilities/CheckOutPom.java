package PomClassUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPom {
    WebDriver driver;
    public CheckOutPom(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "first-name")
    private WebElement Firstname;

    @FindBy (id = "last-name")
    private WebElement Lastname;

    @FindBy (id = "postal-code")
    private WebElement postalcode;

    @FindBy (id = "continue")
    private WebElement ContinueCheckOut;

    //Getters
    public void getFirstname(String value)
    {
        Firstname.clear();
        Firstname.sendKeys(value);
    }
    public void getLastname(String value)
    {
        Lastname.clear();
        Lastname.sendKeys(value);
    }
    public void getZip(String value)
    {
        postalcode.clear();
        postalcode.sendKeys(value);
    }
    public void getContinueCheckOut()
    {
        ContinueCheckOut.click();
    }
}
