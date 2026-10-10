package CartScript;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import GenericUtility.TakingScreenShot;
import PomClassUtilities.CartPom;
import PomClassUtilities.ProductPom;

public class CartNegative extends BaseClass {

    @BeforeMethod(groups = {"negative"})
    public void loginBeforeTest() throws Exception 
    {
        loginToApplication();
    }

    // TC-CART-04
    // Verify empty cart does not incorrectly show a selected product
    @Test(groups = {"negative"})
    public void verifyEmptyCart() throws Exception {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        product.getCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"),"Cart Page was not opened");
        Reporter.log("Cart Page opened successfully",true);

        Assert.assertTrue(cart.isCartEmpty(),"Product is displayed in empty Cart");
        Reporter.log("Empty Cart does not contain any selected product",true);

        TakingScreenShot ts =  new TakingScreenShot();
        ts.getSC(driver, "CartNegative1");
    }

    // TC-CART-05
    // Verify removed product cannot remain as a stale cart item
    @Test(groups = {"negative"})
    public void verifyRemovedProductNotPresent() throws Exception {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        product.getAddTOCart();
        product.getCart();

        cart.getRemove();

        Assert.assertTrue(cart.isCartEmpty(),"Removed product is still present in Cart");
        Reporter.log("Removed product is no longer present in Cart",true);

        TakingScreenShot ts = new TakingScreenShot();
        ts.getSC(driver, "CartNegative2");
    }
}