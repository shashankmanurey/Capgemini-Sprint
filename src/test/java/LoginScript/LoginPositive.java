package LoginScript;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import PomClassUtilities.LoginPom;

public class LoginPositive extends BaseClass {

    @DataProvider(name = "loginPositiveData")
    public Object[][] loginData() throws Exception {

        FileInputStream fis = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");

        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet("Sheet1");

        DataFormatter formatter = new DataFormatter();

        int columnCount = sheet.getRow(0).getLastCellNum();
        int rowCount = 0;

        // Count all non-empty username rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            if (row != null &&
                row.getCell(0) != null &&
                !formatter.formatCellValue(row.getCell(0)).trim().isEmpty()) {

                rowCount++;
            }
        }

        Object[][] data = new Object[rowCount][columnCount];
        int dataIndex = 0;

        // Read ALL valid login data
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            if (row == null || row.getCell(0) == null || formatter.formatCellValue(row.getCell(0)).trim().isEmpty()) 
            {
                continue;
            }

            for (int j = 0; j < columnCount; j++) {

                if (row.getCell(j) != null) 
                {
                    data[dataIndex][j] = formatter.formatCellValue(row.getCell(j));
                } else {
                    data[dataIndex][j] = "";
                }
            }
            dataIndex++;
        }

        workbook.close();
        fis.close();

        return data;
    }

    //TC-LOGIN-01
    //Verify Username, Password and Login controls are displayed
    @Test(groups = {"functional"})
    public void verifyLoginPageControls() {

        LoginPom login = new LoginPom(driver);

        Assert.assertTrue(login.isUsernameDisplayed(),"Username field is not displayed");
        Assert.assertTrue(login.isPasswordDisplayed(), "Password field is not displayed");
        Assert.assertTrue(login.isLoginButtonDisplayed(),"Login button is not displayed");

        Reporter.log("Username field is displayed");
        Reporter.log("Password field is displayed");
        Reporter.log("Login button is displayed");
    }

    // TC-LOGIN-02
    // Valid login
    @Test(dataProvider = "loginPositiveData", groups = {"functional"})
    public void validLoginTest(String username, String password) {

        LoginPom login = new LoginPom(driver);

        login.getUsername(username);
        login.getPassword(password);
        login.getLoginButton();

        Assert.assertTrue(login.isProductsPageDisplayed(),"Product Page was not displayed for user: " + username);

        Reporter.log("Valid Login Successful");
        Reporter.log("Username = " + username);
    }


    // TC-LOGIN-03
    // Login navigates to Product Page
    @Test(dataProvider = "loginPositiveData",groups = {"integration"})
    public void loginNavigationTest(String username, String password) {

        LoginPom login = new LoginPom(driver);

        login.getUsername(username);
        login.getPassword(password);
        login.getLoginButton();

        Assert.assertTrue(login.isProductsPageDisplayed(),"Login did not navigate to Product Page for user: " + username);

        Reporter.log("Navigation successful for: " + username);
    }


    // TC-LOGIN-08
    // Smoke Test
    @Test(dataProvider = "loginPositiveData",groups = {"smoke"})
    public void loginSmokeTest(String username, String password) {

        LoginPom login = new LoginPom(driver);

        login.getUsername(username);
        login.getPassword(password);
        login.getLoginButton();

        Assert.assertTrue(login.isProductsPageDisplayed(),"Smoke test failed for user: " + username);

        Reporter.log("Smoke Test Passed for: " + username);
    }


    // TC-LOGIN-09
    // Regression Test
    @Test(dataProvider = "loginPositiveData",groups = {"regression"})
    public void loginRegressionTest(String username, String password) {

        LoginPom login = new LoginPom(driver);

        login.getUsername(username);
        login.getPassword(password);
        login.getLoginButton();

        Assert.assertTrue(login.isProductsPageDisplayed(), "Regression test failed for user: " + username);

        Reporter.log("Regression Test Passed for: " + username);
    }
}