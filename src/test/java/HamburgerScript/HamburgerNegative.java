
package HamburgerScript;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import GenericUtility.TakingScreenShot;
import PomClassUtilities.HamburgerPom;
import PomClassUtilities.LoginPom;
import PomClassUtilities.ProductPom;
import PomClassUtilities.CartPom;

public class HamburgerNegative extends BaseClass {

    @BeforeMethod(groups = {"negative"})
    public void loginBeforeTest() throws Exception 
    {
        loginToApplication();
    }

    // TC-HAM-04
    // Verify Reset App State with an empty cart
    @Test(groups = {"negative"})
    public void verifyResetWithEmptyCart() throws Exception {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        HamburgerPom ham = new HamburgerPom(driver);

        Assert.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),"Cart badge is displayed when cart should be empty");

        ham.getHam();
        ham.getResetApp();

        Assert.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(), "Cart badge appeared after resetting an empty cart");

        ham.getAllitems();
        product.getCart();

        Assert.assertTrue(cart.isCartEmpty(), "Cart is not empty after resetting an empty cart");

        Reporter.log("Reset App State handles an empty cart correctly", true);

        TakingScreenShot ts = new TakingScreenShot();
        ts.getSC(driver, "HamburgerNegative1");
    }

    // TC-HAM-05
    // Hamburger menu unavailable after Logout
    @Test(groups = {"negative"})
    public void verifyMenuUnavailableAfterLogout() throws Exception {

        HamburgerPom ham = new HamburgerPom(driver);
        LoginPom login = new LoginPom(driver);

        ham.getHam();
        ham.getLogout();

        Assert.assertTrue(login.isLoginButtonDisplayed(),"Login Page was not displayed after Logout");
        Assert.assertTrue(driver.findElements(By.id("react-burger-menu-btn")).isEmpty(), "Hamburger menu is available after Logout");
        Assert.assertFalse(driver.getCurrentUrl().contains("inventory"),"Logged-out user is still on Product Page");

        Reporter.log("Hamburger menu is unavailable after Logout", true);

        TakingScreenShot ts = new TakingScreenShot();
        ts.getSC(driver, "HamburgerNegative2");
    }
}
