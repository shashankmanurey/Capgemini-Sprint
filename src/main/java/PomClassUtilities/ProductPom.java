package PomClassUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPom {
    WebDriver driver;
    public ProductPom(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "//select[@class='product_sort_container']")
    private WebElement SortBtn;

    @FindBy (id = "//a[@data-test='shopping-cart-link']")
    private WebElement CartBtn;

    @FindBy (id = "add-to-cart-sauce-labs-backpack")
    private WebElement AddToCartBtn;

    //Getters
    public void getSort()
    {
        SortBtn.click();
        Select s1 = new Select(SortBtn);
        s1.selectByIndex(1);
    }
    public void getCart()
    {
        CartBtn.click();
    }
    public void getAddTOCart()
    {
        AddToCartBtn.click();
    } 


}
