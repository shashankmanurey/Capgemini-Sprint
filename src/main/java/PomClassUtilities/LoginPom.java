package PomClassUtilities;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class LoginPom {
    WebDriver driver;
    public LoginPom(WebDriver driver)
    {
        this.driver = driver; 
        PageFactory.initElements(driver, this);
    }

    //Locating
    @FindBy (id = "user-name")
    private WebElement username;

    @FindBy (id = "password")
    private WebElement password;

    @FindBy (id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@class=\"title\"]")
    private WebElement productsTitle;

    @FindBy (xpath = "//h3[@data-test=\"error\"]")
    private WebElement errorMessage;

    //Getters 
    public boolean isUsernameDisplayed()
    {
        return username.isDisplayed();
    }
    public boolean isPasswordDisplayed()
    {
        return password.isDisplayed();
    }
    public boolean isLoginButtonDisplayed()
    {
        return loginButton.isDisplayed();
    }
    public void getUsername(String value) 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement user = wait.until(ExpectedConditions.elementToBeClickable(username));
        user.clear();
        user.sendKeys(value);
    }

    public void getPassword(String value) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement pass = wait.until(ExpectedConditions.elementToBeClickable(password));
        pass.clear();
        pass.sendKeys(value);
    }

    public void getLoginButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        button.click();
    }
    public boolean isProductsPageDisplayed()
    {
        try {
            WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));
            return wait.until(ExpectedConditions.visibilityOf(productsTitle)).isDisplayed();
        } catch (TimeoutException e) 
        {
            return false;
        }
    }
    public String getErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();

        } catch (TimeoutException e) {
            return "";
        } catch (NoSuchElementException e) {
            return "";
        }
    }
}
