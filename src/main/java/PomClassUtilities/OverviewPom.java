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

    @FindBy (xpath ="//span[text()='Checkout: Overview']")
    private WebElement OverviewTitle;

    @FindBy (xpath = "//div[@class='inventory_item_name']")
    private WebElement item;

    //Getters
    public void getFinishBtn()
    {
        FinishBtn.click();
    }
    public void getCancelBtn()
    {
        CancelBtn.click();
    }
    public boolean isFinishDisplayed()
    {
        return FinishBtn.isDisplayed();
    }
    public boolean isCancelDisplayed()
    {
        return CancelBtn.isDisplayed();
    }
    public boolean isOverviewDisplayed()
    {
        return OverviewTitle.isDisplayed();
    }
    public boolean isItemDisplayed()
    {
        return item.isDisplayed();
    }
}

