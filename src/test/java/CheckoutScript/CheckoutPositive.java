package CheckoutScript;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import PomClassUtilities.CartPom;
import PomClassUtilities.CheckOutPom;
import PomClassUtilities.CompletePagePom;
import PomClassUtilities.OverviewPom;
import PomClassUtilities.ProductPom;

public class CheckoutPositive extends BaseClass {
	@BeforeMethod(groups = {"functionality", "integration", "smoke", "regression", "system", "bva"})
    public void loginBeforeTest() throws Exception 
    {
        loginToApplication();
    }

    //TC-CHK-01
    //Verify Checkout Information fields and controls
    @Test(groups = {"functionality"})
    public void verifyInfoFields()
    {
        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        CheckOutPom check = new CheckOutPom(driver);

        product.getAddTOCart();
        product.getCart();
        cart.getCheckOut();
        
        Assert.assertTrue(check.firstIsDisplayed(), "Firstname is not displayed");
        Reporter.log("First Name field is displayed on Checkout Information page");

        Assert.assertTrue(check.lastIsDisplayed(), "Lastname is not displayed");
        Reporter.log("Last Name field is displayed on Checkout Information page");

        Assert.assertTrue(check.postalIsDisplayed(), "Postal code is not displayed");
        Reporter.log("Postal Code field is displayed on Checkout Information page");

        Assert.assertTrue(check.continueIsDisplayed(), "Continue button is not displayed");
        Reporter.log("Continue button is displayed on Checkout Information page");

        Assert.assertTrue(check.cancelIsDisplayed(), "Cancel button is not displayed");
        Reporter.log("Cancel button is displayed on Checkout Information page");
    }

    //TC-CHK-02
    //Verify valid checkout information is accepted
    @Test(groups = {"functionality"})
    public void verifyValidCheckoutInformation() throws Exception
    {
        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        CheckOutPom check = new CheckOutPom(driver);
        OverviewPom overview = new OverviewPom(driver);

        product.getAddTOCart();
        product.getCart();
        cart.getCheckOut();

        FileInputStream f = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");
        Workbook wb = WorkbookFactory.create(f);
        Sheet sh = wb.getSheet("Sheet3");

        DataFormatter formatter = new DataFormatter();

        String fname = formatter.formatCellValue(sh.getRow(1).getCell(0));
        String lname = formatter.formatCellValue(sh.getRow(1).getCell(1));
        String zip = formatter.formatCellValue(sh.getRow(1).getCell(2));

        check.getFirstname(fname);
        check.getLastname(lname);
        check.getZip(zip);
        check.getContinueCheckOut();

        Assert.assertTrue(overview.isOverviewDisplayed(), "Checkout Overview is not displayed after submitting valid checkout information");
        Reporter.log("Valid checkout information was accepted and Checkout Overview is displayed");

        wb.close();
        f.close();
    }

    //TC-CHK-03
    //Verify Checkout Overview product visibility and controls
    @Test(groups = {"functionality"})
    public void verifyCheckoutOverview() throws Exception
    {
        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        CheckOutPom check = new CheckOutPom(driver);
        OverviewPom overview = new OverviewPom(driver);

        product.getAddTOCart();
        product.getCart();
        cart.getCheckOut();

        FileInputStream f = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");
        Workbook wb = WorkbookFactory.create(f);
        Sheet sh = wb.getSheet("Sheet3");

        DataFormatter formatter = new DataFormatter();

        String fname = formatter.formatCellValue(sh.getRow(1).getCell(0));
        String lname = formatter.formatCellValue(sh.getRow(1).getCell(1));
        String zip = formatter.formatCellValue(sh.getRow(1).getCell(2));

        check.getFirstname(fname);
        check.getLastname(lname);
        check.getZip(zip);
        check.getContinueCheckOut();

        Assert.assertTrue(overview.isOverviewDisplayed(), "Checkout Overview is not displayed");
        Reporter.log("Checkout Overview is displayed successfully");

        Assert.assertTrue(overview.isItemDisplayed(), "Selected product is not visible on Checkout Overview");
        Reporter.log("Selected product is displayed correctly on Checkout Overview");

        Assert.assertTrue(overview.isFinishDisplayed(), "Finish button is not visible on Checkout Overview");
        Reporter.log("Finish button is displayed on Checkout Overview");

        Assert.assertTrue(overview.isCancelDisplayed(), "Cancel button is not visible on Checkout Overview");
        Reporter.log("Cancel button is displayed on Checkout Overview");

        wb.close();
        f.close();
    }

    //TC-CHK-04
    //Verify Checkout Information to Checkout Overview integration
    @Test(groups = {"integration"})
    public void verifyCheckoutInformationToOverview() throws Exception
    {
        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        CheckOutPom check = new CheckOutPom(driver);
        OverviewPom overview = new OverviewPom(driver);

        product.getAddTOCart();
        product.getCart();
        cart.getCheckOut();

        FileInputStream f = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");
        Workbook wb = WorkbookFactory.create(f);
        Sheet sh = wb.getSheet("Sheet3");

        DataFormatter formatter = new DataFormatter();

        String fname = formatter.formatCellValue(sh.getRow(1).getCell(0));
        String lname = formatter.formatCellValue(sh.getRow(1).getCell(1));
        String zip = formatter.formatCellValue(sh.getRow(1).getCell(2));

        check.getFirstname(fname);
        check.getLastname(lname);
        check.getZip(zip);
        check.getContinueCheckOut();

        Assert.assertTrue(overview.isOverviewDisplayed(),"Checkout Overview is not displayed after submitting checkout information");
        Reporter.log("Checkout Information successfully navigated to Checkout Overview");

        Assert.assertTrue(overview.isItemDisplayed(), "Selected product is not visible on Checkout Overview");
        Reporter.log("Selected product is retained on Checkout Overview");

        wb.close();
        f.close();
    }

    //TC-CHK-05
    //Verify Checkout Complete page and Back Home
    @Test(groups = {"integration"})
    public void verifyCheckoutCompletePage() throws  Exception
    {
        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        CheckOutPom check   = new CheckOutPom(driver);
        OverviewPom overview = new OverviewPom(driver);
        CompletePagePom complete = new CompletePagePom(driver);

        product.getAddTOCart();
        product.getCart();
        cart.getCheckOut();

        FileInputStream f = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");
        Workbook wb = WorkbookFactory.create(f);
        Sheet sh = wb.getSheet("Sheet3");

        DataFormatter formatter = new DataFormatter();

        String fname = formatter.formatCellValue(sh.getRow(1).getCell(0));
        String lname = formatter.formatCellValue(sh.getRow(1).getCell(1));
        String zip = formatter.formatCellValue(sh.getRow(1).getCell(2));

        check.getFirstname(fname);
        check.getLastname(lname);
        check.getZip(zip);
        check.getContinueCheckOut();

        overview.getFinishBtn();

        Assert.assertTrue(complete.isThankyouDisplayed(), "Thank you for your order message is not visible on Checkout Complete page");
        Reporter.log("Thank you for your order message is displayed on Checkout Complete page");

        complete.getBackBtn();

        Assert.assertTrue(product.isProductDisplayed(), "Products page is not visible after clicking Back Home");
        Reporter.log("Products page is displayed successfully after clicking Back Home");

        wb.close();
        f.close();
    }

    //TC-CHK-09
    //Verify checkout regression flow
    @Test(groups = {"regression"})
    public void verifyCheckoutRegressionFlow() throws  Exception
    {
        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        CheckOutPom check = new CheckOutPom(driver);
        OverviewPom overview = new OverviewPom(driver);
        CompletePagePom complete = new CompletePagePom(driver);

        product.getAddTOCart();
        product.getCart();
        cart.getCheckOut();

        FileInputStream f = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");
        Workbook wb = WorkbookFactory.create(f);
        Sheet sh = wb.getSheet("Sheet3");

        DataFormatter formatter = new DataFormatter();

        String fname = formatter.formatCellValue(sh.getRow(1).getCell(0));
        String lname = formatter.formatCellValue(sh.getRow(1).getCell(1));
        String zip = formatter.formatCellValue(sh.getRow(1).getCell(2));

        check.getFirstname(fname);
        check.getLastname(lname);
        check.getZip(zip);
        check.getContinueCheckOut();

        overview.getFinishBtn();

        Assert.assertTrue(complete.isThankyouDisplayed(), "Order completion confirmation is not displayed");
        Reporter.log("Checkout regression flow completed successfully and order confirmation is displayed");

        wb.close();
        f.close();
    }

    //TC-CHK-10
    // Verify complete system purchase flow
    @Test(groups = {"system"})
    public void verifyCompleteSystemPurchaseFlow() throws  Exception
    {
        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        CheckOutPom check = new CheckOutPom(driver);
        OverviewPom overview = new OverviewPom(driver);
        CompletePagePom complete = new CompletePagePom(driver);

        Assert.assertTrue(product.isCartDisplayed(),"Cart is not displayed after adding product");
        Reporter.log("Login successful and Products page is displayed");

        product.getAddTOCart();
        Assert.assertTrue(product.isCartDisplayed(), "Cart is not displayed after adding product");
        Reporter.log("Product added to cart successfully");

        product.getCart();
        Assert.assertTrue(cart.isProductDisplayed(), "Selected product is not displayed in Cart");
        Reporter.log("Cart opened and selected product is displayed");

        cart.getCheckOut();
        Assert.assertTrue(check.firstIsDisplayed(), "Checkout Information page is not displayed");
        Reporter.log("Checkout Information page is displayed");

        FileInputStream f = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");
        Workbook wb = WorkbookFactory.create(f);
        Sheet sh = wb.getSheet("Sheet3");

        DataFormatter formatter = new DataFormatter();

        String fname = formatter.formatCellValue(sh.getRow(1).getCell(0));
        String lname = formatter.formatCellValue(sh.getRow(1).getCell(1));
        String zip = formatter.formatCellValue(sh.getRow(1).getCell(2));

        check.getFirstname(fname);
        check.getLastname(lname);
        check.getZip(zip);
        Reporter.log("Valid checkout information entered successfully");

        check.getContinueCheckOut();

        Assert.assertTrue(overview.isOverviewDisplayed(), "Checkout Overview page is not displayed");
        Reporter.log("Checkout Information submitted and Checkout Overview is displayed");

        overview.getFinishBtn();

        Assert.assertTrue(complete.isThankyouDisplayed(), "Complete purchase flow failed");
        Reporter.log("Complete system purchase flow completed successfully and order confirmation is displayed");

        wb.close();
        f.close();
    }
}
