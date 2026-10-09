package CartScript;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import PomClassUtilities.CartPom;
import PomClassUtilities.LoginPom;
import PomClassUtilities.ProductPom;

public class CartPositive extends BaseClass {

    @BeforeMethod
    public void loginToApplication() {

        LoginPom login = new LoginPom(driver);

        login.getUsername("standard_user");
        login.getPassword("secret_sauce");
        login.getLoginButton();

        Reporter.log("Logged in successfully and navigated to Product Page",true);
    }

    // TC-CART-01
    // Verify added product is visible in Cart Page
    @Test(groups = {"functional"})
    public void verifyAddedProductVisibleInCart() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        product.getAddTOCart();
        product.getCart();

        Assert.assertTrue(cart.isProductDisplayed(),"Added product is not displayed in Cart Page");
        Reporter.log("Added product is displayed in Cart Page",true);
    }

    // TC-CART-02
    // Verify Remove, Checkout and Continue Shopping controls
    @Test(groups = {"functional"})
    public void verifyCartControls() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        product.getAddTOCart();
        product.getCart();

        Assert.assertTrue(cart.isRemoveDisplayed(), "Remove button is not displayed");
        Reporter.log("Remove button is displayed", true);

        Assert.assertTrue(cart.isCheckoutDisplayed(), "Checkout button is not displayed");
        Reporter.log("Checkout button is displayed",true);

        Assert.assertTrue(cart.isContinueShoppingDisplayed(),"Continue Shopping button is not displayed");
        Reporter.log("Continue Shopping button is displayed",true);
    }

    // TC-CART-03
    // Verify Cart Page to Checkout Page using Checkout
    @Test(groups = {"integration"})
    public void verifyCartToCheckout() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        product.getAddTOCart();
        product.getCart();

        cart.getCheckOut();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"),"Checkout Information Page was not opened");
        Reporter.log("Cart Page to Checkout Page navigation successful",true);
    }

    // TC-CART-06
    // Verify Cart Page smoke test
    @Test(groups = {"smoke"})
    public void cartPageSmokeTest() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        product.getAddTOCart();
        product.getCart();

        Assert.assertTrue(cart.isProductDisplayed(),"Product is not displayed in Cart Page");
        Reporter.log("Product is displayed in Cart Page",true);

        Assert.assertTrue(cart.isCheckoutDisplayed(),"Checkout button is not displayed");
        Reporter.log("Checkout control is available",true);
    }

    // TC-CART-07
    // Verify Continue Shopping smoke test
    @Test(groups = {"smoke"})
    public void continueShoppingSmokeTest() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        product.getAddTOCart();
        product.getCart();

        cart.getContinueBtn();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Product Page was not opened after Continue Shopping");
        Reporter.log("Continue Shopping successfully navigated to Product Page",true);
    }
    
    // TC-CART-08
    // Verify cart contents after returning from Product Page
    @Test(groups = {"regression"})
    public void verifyCartContentsAfterReturning() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        product.getAddTOCart();
        product.getCart();

        Assert.assertTrue(cart.isProductDisplayed(),"Product is not displayed in Cart");
        Reporter.log("Product is displayed in Cart",true);

        cart.getContinueBtn();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),"Product Page was not opened");
        Reporter.log("Returned to Product Page successfully",true);

        product.getCart();

        Assert.assertTrue(cart.isProductDisplayed(),"Product is not retained in Cart after returning");
        Reporter.log("Cart contents are retained after returning from Product Page",true);
    }

    // TC-CART-09
    // Verify Remove action after returning to Cart
    @Test(groups = {"regression"})
    public void verifyRemoveAfterReturningToCart() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        product.getAddTOCart();
        product.getCart();

        cart.getContinueBtn();

        product.getCart();

        cart.getRemove();

        Assert.assertTrue(cart.isCartEmpty(),"Product is still present after Remove action");
        Reporter.log("Remove action works correctly after returning to Cart",true);
    }

    // TC-CART-10
    // Verify cart quantity boundary 0 -> 1 -> 0
    @Test(groups = {"bva"})
    public void verifyCartQuantityBoundary() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        // Boundary: 0 -> 1
        product.getAddTOCart();
        product.getCart();

        Assert.assertTrue(cart.isProductDisplayed(),"Exactly one selected product is not displayed in Cart");
        Reporter.log("Cart boundary changed from 0 to 1 item successfully",true);

        // Boundary: 1 -> 0
        cart.getRemove();

        Assert.assertTrue(cart.isCartEmpty(),"Cart is not empty after removing the product");
        Reporter.log("Cart boundary changed from 1 to 0 items successfully",true);
    }
}