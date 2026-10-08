package PomClassUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        RemoveBtn.click();
    }
    public void getCheckOut()
    {
        CheckoutBtn.click();
    }
    public void getContinueBtn()
    {
        ContinueBtn.click();
    }
}
