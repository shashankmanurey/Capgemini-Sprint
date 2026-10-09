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
import GenericUtility.TakingScreenShot;
import PomClassUtilities.CartPom;
import PomClassUtilities.CheckOutPom;
import PomClassUtilities.LoginPom;
import PomClassUtilities.ProductPom;

public class CheckoutNegative extends BaseClass {
    @BeforeMethod
    public void loginToApplication()
    {
        LoginPom login = new LoginPom(driver);
        login.getUsername("standard_user");
        login.getPassword("secret_sauce");
        login.getLoginButton();
    }

    // TC-CHK-06
    // Verify blank First Name is rejected
    @Test(groups = {"Negative"})
    public void verifyBlankFirstNameIsRejected() throws Exception
    {
        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        CheckOutPom check = new CheckOutPom(driver);

        product.getAddTOCart();
        product.getCart();
        cart.getCheckOut();

        FileInputStream f = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");
        Workbook wb = WorkbookFactory.create(f);
        Sheet sh = wb.getSheet("Sheet4");

        DataFormatter formatter = new DataFormatter();

        String fname = formatter.formatCellValue(sh.getRow(1).getCell(0));
        String lname = formatter.formatCellValue(sh.getRow(1).getCell(1));
        String zip = formatter.formatCellValue(sh.getRow(1).getCell(2));

        check.getFirstname(fname);
        check.getLastname(lname);
        check.getZip(zip);
        check.getContinueCheckOut();

        Assert.assertTrue(check.firstIsDisplayed(), "Checkout proceeded despite blank First Name");
        Reporter.log("Blank First Name was rejected and Checkout Information page remained displayed");

        TakingScreenShot ts = new TakingScreenShot();
        ts.getSC(driver, "CheckOutNegative1");

        wb.close();
        f.close();
    }

    // TC-CHK-07
    // Verify blank Last Name is rejected
    @Test(groups = {"Negative"})
    public void verifyBlankLastNameIsRejected() throws Exception
    {
        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        CheckOutPom check = new CheckOutPom(driver);

        product.getAddTOCart();
        product.getCart();
        cart.getCheckOut();

        FileInputStream f = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");
        Workbook wb = WorkbookFactory.create(f);
        Sheet sh = wb.getSheet("Sheet4");

        DataFormatter formatter = new DataFormatter();

        String fname = formatter.formatCellValue(sh.getRow(2).getCell(0));
        String lname = formatter.formatCellValue(sh.getRow(2).getCell(1));
        String zip = formatter.formatCellValue(sh.getRow(2).getCell(2));

        check.getFirstname(fname);
        check.getLastname(lname);
        check.getZip(zip);
        check.getContinueCheckOut();

        Assert.assertTrue(check.lastIsDisplayed(), "Checkout proceeded despite blank Last Name");
        Reporter.log("Blank Last Name was rejected and Checkout Information page remained displayed");

        TakingScreenShot ts = new TakingScreenShot();
        ts.getSC(driver, "CheckOutNegative2");

        wb.close();
        f.close();
    }

    // TC-CHK-08
    // Verify blank Zip is rejected
    @Test(groups = {"Negative"})
    public void verifyBlankZipIsRejected() throws Exception
    {
        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        CheckOutPom check = new CheckOutPom(driver);

        product.getAddTOCart();
        product.getCart();
        cart.getCheckOut();

        FileInputStream f = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");
        Workbook wb = WorkbookFactory.create(f);
        Sheet sh = wb.getSheet("Sheet4");

        DataFormatter formatter = new DataFormatter();

        String fname = formatter.formatCellValue(sh.getRow(3).getCell(0));
        String lname = formatter.formatCellValue(sh.getRow(3).getCell(1));
        String zip = formatter.formatCellValue(sh.getRow(3).getCell(2));

        check.getFirstname(fname);
        check.getLastname(lname);
        check.getZip(zip);
        check.getContinueCheckOut();

        Assert.assertTrue(check.postalIsDisplayed(), "Checkout proceeded despite blank Zip Code");
        Reporter.log("Blank Zip Code was rejected and Checkout Information page remained displayed");

        TakingScreenShot ts = new TakingScreenShot();
        ts.getSC(driver, "CheckOutNegative3");

        wb.close();
        f.close();
    }
}
