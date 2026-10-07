package BaseClassUtility;

import java.io.FileInputStream;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseClass {

    protected WebDriver driver;

    @BeforeSuite
    public void Bs() {
        System.out.println("Open Database connectivity");
    }

    @AfterSuite
    public void As() {
        System.out.println("Close Database connectivity");
    }

    @BeforeTest
    public void Bt() {
        System.out.println("Pre-conditions");
    }

    @AfterTest
    public void At() {
        System.out.println("Post-conditions");
    }

    @BeforeClass
    public void Bc() throws Exception {
        FileInputStream f = new FileInputStream("src/main/resources/DDT/common.properties");

        Properties p = new Properties();
        p.load(f);
        f.close();

        String browser = p.getProperty("browser");

        if (browser == null) {
            throw new RuntimeException("Browser is missing in data1.properties");
        }

        if (browser.equalsIgnoreCase("chrome")) {
            
        	final Map<String, Object> chromePrefs = new HashMap<>();
        	chromePrefs.put("credentials_enable_service", false);
        	chromePrefs.put("profile.password_manager_enabled", false);
        	chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

        	final ChromeOptions chromeOptions = new ChromeOptions();
        	chromeOptions.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(chromeOptions);

        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported Browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("Browser launched: " + browser);
    }

    @BeforeMethod
    public void openApplication() throws Exception {

        FileInputStream f = new FileInputStream("src/main/resources/DDT/common.properties");

        Properties p = new Properties();
        p.load(f);
        f.close();

        String url = p.getProperty("url");

        if (url == null) {
            throw new RuntimeException("URL is missing in data1.properties");
        }

        driver.get(url);

        System.out.println("Opening SauceDemo");
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }

    @AfterClass
    public void Ac() {

        System.out.println("Quit browser");

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}