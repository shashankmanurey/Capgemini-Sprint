package CompleteWorkflow;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import BaseClassUtility.BaseClass;
import PomClassUtilities.CartPom;
import PomClassUtilities.CheckOutPom;
import PomClassUtilities.CompletePagePom;
import PomClassUtilities.HamburgerPom;
import PomClassUtilities.LoginPom;
import PomClassUtilities.OverviewPom;
import PomClassUtilities.ProductPom;

public class CompleteSystemWorkflow extends BaseClass {
    @BeforeMethod 
    public void loginBeforeTest() throws Exception
    {
        loginToApplication();
    }

    @Test 
    public void verifyCompleteWorkflow() throws Exception
    {
        LoginPom login = new LoginPom(driver);
        ProductPom product = new ProductPom(driver);
        CartPom cart = new CartPom(driver);
        CheckOutPom check = new CheckOutPom(driver);
        CompletePagePom complete = new CompletePagePom(driver);
        HamburgerPom ham = new HamburgerPom(driver);
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

        overview.getFinishBtn();
        Assert.assertTrue(complete.isThankyouDisplayed(), "Order could not be placed");
        Reporter.log("Order placed successfully");

        complete.getBackBtn();
        ham.getHam();
        ham.getLogout();

        Assert.assertTrue(login.isUsernameDisplayed(), "Logout could not be performed after order placed");
        Reporter.log("Order placed successfully. Loged out of the system and complete system workflow completed");

    }
}
