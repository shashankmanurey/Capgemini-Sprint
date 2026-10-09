package LoginScript;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Reporter;

import BaseClassUtility.BaseClass;
import GenericUtility.TakingScreenShot;
import PomClassUtilities.LoginPom;

public class LoginNegative extends BaseClass {
    @DataProvider(name = "loginNegativeData")
    public Object[][] loginData() throws Exception
    {
        //Excel file path
        FileInputStream fis = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");

        //workbook
        Workbook workbook = WorkbookFactory.create(fis);

        //Sheet
        Sheet sheet = workbook.getSheet("Sheet2");

        // Get row and column count
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];

        DataFormatter formatter = new DataFormatter();

        // Read Excel data
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                data[i - 1][j] = formatter.formatCellValue(row.getCell(j));
            }
        }

        workbook.close();
        fis.close();

        return data;
    }

    @Test(dataProvider = "loginNegativeData", groups = {"negative"})
    public void loginTest(String username, String password) throws Exception
    {
        LoginPom login = new LoginPom(driver);

        login.getUsername(username);
        login.getPassword(password);
        login.getLoginButton();

        String actualError = login.getErrorMessage();
        Reporter.log("Error Message: " + actualError);
        Reporter.log("Username = " + username + " Password = " + password);

        Assert.assertFalse(actualError.isEmpty(), "Error message was not displayed");

        //Take screenshot of failure
        // TakesScreenshot t = (TakesScreenshot) driver;

        // File src = t.getScreenshotAs(OutputType.FILE);

        // String fileName = "Screenshots/LoginNegative_" + username.replaceAll("[^a-zA-Z0-9]", "_")+ "_"+ password.replaceAll("[^a-zA-Z0-9]", "_")+ ".png";
        // File dst = new File(fileName);
        // FileHandler.copy(src, dst);

        // System.out.println("Screenshot saved: " + dst.getAbsolutePath());
        TakingScreenShot ts = new TakingScreenShot();
        ts.getSC(driver, "LoginNegative");
    }
}

