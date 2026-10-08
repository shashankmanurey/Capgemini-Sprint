package GenericUtility;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.WebDriver;

public class TakingScreenShot {
    public void getSC(WebDriver driver, String moduleName) throws Exception
    {
        TakesScreenshot t = (TakesScreenshot) driver;

        File src = t.getScreenshotAs(OutputType.FILE);

        String fileName = "Screenshots/"+ moduleName + ".png";
        File dst = new File(fileName);
        FileHandler.copy(src, dst);
    }
}
