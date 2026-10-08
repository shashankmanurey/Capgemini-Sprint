package PomClassUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OverviewPom {
    WebDriver driver;
    public OverviewPom(WebDriver driver)
    {   
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "finish")
    private WebElement FinishBtn;

    @FindBy (id = "cancel")
    private WebElement CancelBtn;

    //Getters
    public void getFinishBtn()
    {
        FinishBtn.click();
    }
    public void getCancelBtn()
    {
        CancelBtn.click();
    }
}

