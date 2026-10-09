
package HamburgerScript;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import PomClassUtilities.HamburgerPom;
import PomClassUtilities.ProductPom;
import PomClassUtilities.CartPom;

public class HamburgerPositive extends BaseClass {

    @BeforeMethod
    public void loginBeforeTest() throws Exception 
    {
        loginToApplication();
    }

    // TC-HAM-02
    //Verify Hamburger menu options are displayed
    @Test(groups = {"functional"})
    public void verifyHamburgerMenu()
    {
        HamburgerPom ham = new HamburgerPom(driver);

        ham.getHam();
        Assert.assertTrue(ham.isHamButtondisplayed(), "Hamburger button isnt visible");
        Reporter.log("Hamburger button is displayed");

        Assert.assertTrue(ham.isAllItemsdisplayed(), "All Items is not Displayed");
        Reporter.log("All Items is displayed");

        Assert.assertTrue(ham.isAboutdisplayed());
        Reporter.log("About is displayed");

        Assert.assertTrue(ham.isLogoutdisplayed());
        Reporter.log("Logout is displayed");

        Assert.assertTrue(ham.isResetAppdisplayed());
        Reporter.log("Reset App is displayed");

    }

    // TC-HAM-02
    // Verify Reset App State removes added product
    @Test(groups = {"functional"})
    public void verifyResetAppStateRemovesProduct() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        HamburgerPom ham = new HamburgerPom(driver);

        product.getAddTOCart();

        Assert.assertFalse(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),"Cart badge is not displayed after adding product");

        ham.getHam();
        ham.getResetApp();

        Assert.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),"Cart badge is still displayed after Reset App State");
        Reporter.log("Cart badge cleared after Reset App State", true);

        product.getCart();

        Assert.assertTrue(cart.isCartEmpty(),"Cart is not empty after Reset App State");
        Reporter.log("Reset App State successfully cleared the cart", true);
    }

    // TC-HAM-03
    // Verify Logout navigates to Login Page
    @Test(groups = {"integration"})
    public void verifyLogoutNavigation() {

        HamburgerPom ham = new HamburgerPom(driver);

        ham.getHam();
        ham.getLogout();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),"User was not redirected to SauceDemo");

        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(),"Login Page was not displayed after Logout");
        Reporter.log("Logout navigated to Login Page successfully", true);
    }

    // TC-HAM-06
    // Verify Hamburger menu smoke test
    @Test(groups = {"smoke"})
    public void hamburgerMenuSmokeTest() {

        HamburgerPom ham = new HamburgerPom(driver);

        ham.getHam();

        Assert.assertTrue(ham.isAllItemsdisplayed(),"All Items link is not displayed");
        Reporter.log("All Items link is displayed", true);

        Assert.assertTrue(ham.isLogoutdisplayed(),"Logout link is not displayed");
        Reporter.log("Logout link is displayed", true);

        Assert.assertTrue(ham.isResetAppdisplayed(),"Reset App State link is not displayed");
        Reporter.log("Reset App State link is displayed", true);

        ham.closeMenu();

        Reporter.log("Hamburger menu smoke test passed", true);
    }

    // TC-HAM-07
    // Verify Reset App State smoke test
    @Test(groups = {"smoke"})
    public void resetAppStateSmokeTest() {

        ProductPom product = new ProductPom(driver);
        HamburgerPom ham = new HamburgerPom(driver);

        product.getAddTOCart();

        ham.getHam();
        ham.getResetApp();

        Assert.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(), "Cart indicator was not reset");
        Reporter.log("Reset App State smoke test passed", true);
    }

    // TC-HAM-08
    // Verify Logout after navigating through Product and Cart
    @Test(groups = {"regression"})
    public void verifyLogoutAfterNavigation() {

        ProductPom product = new ProductPom(driver);
        HamburgerPom ham = new HamburgerPom(driver);

        product.getCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"),"Cart Page was not opened");
        Reporter.log("Navigated to Cart Page successfully", true);

        CartPom cart = new CartPom(driver);
        cart.getContinueBtn();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Product Page was not opened");

        ham.getHam();
        ham.getLogout();

        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Logout failed after navigation");
        Reporter.log("Logout works after Product and Cart navigation", true);
    }

    // TC-HAM-09
    // Verify Reset App State after adding and removing products
    @Test(groups = {"regression"})
    public void verifyResetAfterAddAndRemove() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        HamburgerPom ham = new HamburgerPom(driver);

        product.getAddTOCart();

        product.getCart();
        cart.getRemove();

        Assert.assertTrue(cart.isCartEmpty(), "Cart is not empty after removing product");

        cart.getContinueBtn();

        product.getAddTOCart();

        Assert.assertFalse(driver.findElements(By.className("shopping_cart_badge")).isEmpty(), "Cart badge is missing after adding product again");

        ham.getHam();
        ham.getResetApp();

        Assert.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(), "Cart badge remains after Reset App State");
        Reporter.log("Reset App State cleared the cart after add/remove operations", true);
    }

    // TC-HAM-10
    // Verify complete Hamburger menu workflow
    @Test(groups = {"system"})
    public void verifyCompleteHamburgerWorkflow() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        HamburgerPom ham = new HamburgerPom(driver);

        product.getAddTOCart();
        ham.getHam();

        Assert.assertTrue(ham.isAllItemsdisplayed(), "All Items link is not displayed");
        Assert.assertTrue(ham.isLogoutdisplayed(), "Logout link is not displayed");
        Assert.assertTrue(ham.isResetAppdisplayed(), "Reset App State link is not displayed");

        Reporter.log("Hamburger menu controls are displayed", true);

        ham.closeMenu();
        product.getCart();

        Assert.assertTrue(cart.isProductDisplayed(), "Added product is not present in Cart");
        Reporter.log("Added product is present in Cart", true);

        cart.getContinueBtn();

        ham.getHam();
        ham.getResetApp();

        Assert.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(), "Cart badge remains after reset");
        Reporter.log("Cart state reset successfully", true);

        ham.closeMenu();
        ham.getHam();
        ham.getLogout();

        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Login Page was not displayed after Logout");
        Reporter.log("Complete Hamburger menu workflow passed", true);
    }
}
