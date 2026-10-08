package ProductScript;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import GenericUtility.TakingScreenShot;
import PomClassUtilities.CartPom;
import PomClassUtilities.LoginPom;
import PomClassUtilities.ProductPom;

public class ProductNegative extends BaseClass {

    @BeforeMethod
    public void loginToApplication() {

        LoginPom login = new LoginPom(driver);

        login.getUsername("standard_user");
        login.getPassword("secret_sauce");
        login.getLoginButton();

        Reporter.log("Logged in successfully and navigated to Product Page", true);
    }

    // TC-PROD-04
    // Verify Add to Cart is not incorrectly retained after product removal
    @Test(groups = {"negative"})
    public void verifyProductAfterRemoval() throws Exception{

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        product.getAddTOCart();
        product.getCart();

        cart.getRemove();
        cart.getContinueBtn();

        Assert.assertTrue(product.isAddToCartDisplayed(), "Add to Cart button is not available after product removal");
        Reporter.log("Add to Cart is available again after product removal", true);

        //Taking Screenshot 
        TakingScreenShot ts = new TakingScreenShot();
        ts.getSC(driver, "ProductNegative1");
    }

    // TC-PROD-05
    // Verify cart navigation without a selected product
    @Test(groups = {"negative"})
    public void verifyEmptyCartNavigation() throws Exception {

        ProductPom product = new ProductPom(driver);

        product.getCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Cart Page was not opened");
        Reporter.log("Cart Page opened successfully with no selected product",true);

        Assert.assertFalse(driver.getPageSource().contains("Sauce Labs Backpack"),"Product is present in empty cart");
        Reporter.log("Empty Cart state is handled correctly",true);

        //Taking Screenshot
        TakingScreenShot ts = new TakingScreenShot();
        ts.getSC(driver, "ProductNegative2");
    }

}