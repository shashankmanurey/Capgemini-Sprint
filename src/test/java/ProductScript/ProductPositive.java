package ProductScript;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import PomClassUtilities.CartPom;
import PomClassUtilities.ProductPom;

public class ProductPositive extends BaseClass {

    @BeforeMethod
    public void loginBeforeTest() throws Exception 
    {
        loginToApplication();
    }

    // TC-PROD-01
    // Verify products are visible on Product Page
    @Test(groups = {"functional"})
    public void verifyProductsVisible() {

        ProductPom product = new ProductPom(driver);
        Assert.assertTrue(product.isProductDisplayed(), "Products are not displayed on Product Page");

        Assert.assertTrue(product.isProductNameDisplayed(), "Product name is not displayed");

        Reporter.log("Products are visible on Product Page");
    }

    // TC-PROD-02
    // Verify Add to Cart and Cart Logo behavior
    @Test(groups = {"functional"})
    public void verifyAddToCart() {

        ProductPom product = new ProductPom(driver);

        product.getAddTOCart();
        product.getCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Cart Page was not opened");
        Reporter.log("Product added to cart and Cart Page opened successfully", true);
    }

    // TC-PROD-03
    // Verify Product Page to Cart Page using Cart Logo
    @Test(groups = {"integration"})
    public void verifyProductPageToCart() {

        ProductPom product = new ProductPom(driver);

        product.getAddTOCart();
        product.getCart();

        Assert.assertTrue(
        driver.getCurrentUrl().contains("cart"), "Cart Page was not opened");
        Reporter.log("Product Page to Cart Page navigation successful", true);

        Assert.assertTrue(driver.getPageSource().contains("Sauce Labs Backpack"), "Selected product is not visible in Cart");
        Reporter.log("Sauce Labs Backpack is visible in Cart", true);
    }

    // TC-PROD-06
    // Verify Product Page smoke test
    @Test(groups = {"smoke"})
    public void productPageSmokeTest() {

        ProductPom product = new ProductPom(driver);

        Assert.assertTrue(product.isProductDisplayed(),"Products are not displayed");
        Reporter.log("Products are displayed on Product Page", true);

        product.getAddTOCart();

        Assert.assertTrue(product.isCartDisplayed(),"Cart Logo is not displayed");
        Reporter.log("Product Page Smoke Test Passed", true);
    }

    // TC-PROD-07
    // Verify filter drop-down smoke test
    @Test(groups = {"smoke"})
    public void filterDropdownSmokeTest() {

        ProductPom product = new ProductPom(driver);

        // Name A to Z
        product.sortByNameAZ();
        Assert.assertTrue(product.isProductDisplayed(), "Products are not displayed after Name A to Z sorting");
        Reporter.log("Name A to Z filter passed", true);

        // Name Z to A
        product.sortByNameZA();
        Assert.assertTrue(product.isProductDisplayed(), "Products are not displayed after Name Z to A sorting");
        Reporter.log("Name Z to A filter passed", true);

        // Price Low to High
        product.sortByPriceLowToHigh();
        Assert.assertTrue(product.isProductDisplayed(), "Products are not displayed after Price Low to High sorting");
        Reporter.log("Price Low to High filter passed", true);

        // Price High to Low
        product.sortByPriceHighToLow();Assert.assertTrue(product.isProductDisplayed(),"Products are not displayed after Price High to Low sorting");
        Reporter.log("Price High to Low filter passed", true);
    }

    // TC-PROD-08
    // Verify product visibility after sorting
    @Test(groups = {"regression"})
    public void verifyProductVisibilityAfterSorting() {

        ProductPom product = new ProductPom(driver);

        product.sortByNameAZ();

        Assert.assertTrue(product.isProductDisplayed(), "Products are not visible after Name A to Z sorting");
        Reporter.log("Products remain visible after Name A to Z sorting",true);

        product.sortByPriceLowToHigh();

        Assert.assertTrue(product.isProductDisplayed(), "Products are not visible after Price Low to High sorting");
        Reporter.log("Products remain visible after Price Low to High sorting", true);
    }

    // TC-PROD-09
    // Verify Add to Cart after changing filter
    @Test(groups = {"regression"})
    public void verifyAddToCartAfterFilter() {

        ProductPom product = new ProductPom(driver);

        product.sortByNameZA();
        product.getAddTOCart();

        Assert.assertTrue(product.isCartDisplayed(), "Cart Logo is not displayed after adding product");
        Reporter.log("Add to Cart after filter Regression Test Passed", true);
    }
    
    // TC-PROD-10
    // Verify Cart Logo boundary state 0 -> 1 -> 0
    @Test(groups = {"bva"})
    public void verifyCartLogoBoundaryState() {

        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);

        product.getAddTOCart();
        product.getCart();

        Assert.assertTrue(driver.getPageSource().contains("Sauce Labs Backpack"), "Product was not added to cart");
        Reporter.log("Cart changed from 0 to 1 item successfully", true);

        cart.getRemove();

        Assert.assertFalse(driver.getPageSource().contains("Sauce Labs Backpack"), "Product is still present after removal");
        Reporter.log("Cart changed from 1 to 0 items successfully", true);
    }

}