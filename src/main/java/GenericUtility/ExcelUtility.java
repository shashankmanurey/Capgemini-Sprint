package GenericUtility;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

    public String getData(String sheetName, int rowNum, int cellNum) throws Exception {

        FileInputStream fis = new FileInputStream("src/test/resources/SauceDemoCreds.xlsx");

        Workbook workbook = WorkbookFactory.create(fis);

        DataFormatter formatter = new DataFormatter();

        String data = formatter.formatCellValue(workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum));

        workbook.close();
        fis.close();

        return data;
    }
}