package PomClassUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPom {

    WebDriver driver;

    public ProductPom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Product Page
    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement SortBtn;

    @FindBy(xpath = "//a[@data-test='shopping-cart-link']")
    private WebElement CartBtn;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement AddToCartBtn;

    @FindBy(xpath = "//div[@data-test='inventory-item']")
    private WebElement ProductCard;

    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    private WebElement ProductName;

    //Getters
    public void getSort() {
        Select s1 = new Select(SortBtn);
        s1.selectByIndex(1);
    }

    public void sortByNameAZ() {
        Select s1 = new Select(SortBtn);
        s1.selectByValue("az");
    }

    public void sortByNameZA() {
        Select s1 = new Select(SortBtn);
        s1.selectByValue("za");
    }

    public void sortByPriceLowToHigh() {
        Select s1 = new Select(SortBtn);
        s1.selectByValue("lohi");
    }

    public void sortByPriceHighToLow() {
        Select s1 = new Select(SortBtn);
        s1.selectByValue("hilo");
    }

    public void getCart() {
        CartBtn.click();
    }

    public void getAddTOCart() {
        AddToCartBtn.click();
    }

    public boolean isProductDisplayed() {
        return ProductCard.isDisplayed();
    }

    public boolean isProductNameDisplayed() {
        return ProductName.isDisplayed();
    }

    public boolean isAddToCartDisplayed() {
        return AddToCartBtn.isDisplayed();
    }

    public boolean isCartDisplayed() {
        return CartBtn.isDisplayed();
    }
}