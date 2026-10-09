package PomClassUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompletePagePom {
    WebDriver driver;
    public CompletePagePom(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "back-to-products")
    private WebElement BackBtn;

    @FindBy (id = "generate-pdf-order")
    private WebElement GenPdf;

    //Getters
    public void getBackBtn()
    {
        BackBtn.click();
    }
    public void getGenPdf()
    {
        GenPdf.click();
    }
    public boolean isThankyouDisplayed()
    {
        return BackBtn.isDisplayed();
    }
}
