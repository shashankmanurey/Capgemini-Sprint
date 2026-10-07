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

import BaseClassUtility.BaseClass;
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

    @Test(dataProvider = "loginNegativeData")
    public void loginTest(String username, String password) 
    {
        LoginPom login = new LoginPom(driver);

        login.getUsername(username);
        login.getPassword(password);
        login.getLoginButton();

        String actualError = login.getErrorMessage();
        System.out.println("Error Message: " + actualError);
        System.out.println("Username = " + username + " Password = " + password);

        Assert.assertFalse(actualError.isEmpty(), "Error message was not displayed");
    }
}

