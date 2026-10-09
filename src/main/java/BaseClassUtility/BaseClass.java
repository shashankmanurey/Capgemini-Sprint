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
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import PomClassUtilities.LoginPom;

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

    @BeforeMethod
    public void openApplication() throws Exception {

        FileInputStream f = new FileInputStream("src/main/resources/DDT/common.properties");

        Properties p = new Properties();
        p.load(f);
        f.close();

        String browser = p.getProperty("browser");
        String url = p.getProperty("url");

        if (browser == null) {
            throw new RuntimeException("Browser is missing in common.properties");
        }

        if (url == null) {
            throw new RuntimeException("URL is missing in common.properties");
        }

        if (browser.equalsIgnoreCase("chrome")) {

            final Map<String, Object> chromePrefs = new HashMap<>();

            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromePrefs.put("profile.password_manager_leak_detection", false);

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

        driver.get(url);

        System.out.println("Browser launched: " + browser);
        System.out.println("Opening SauceDemo");
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }

    public void loginToApplication() throws Exception 
    {
        FileInputStream f = new FileInputStream("src/main/resources/DDT/common.properties");

        Properties p = new Properties();
        p.load(f);
        f.close();

        String username = p.getProperty("username");
        String password = p.getProperty("password");

        if (username == null || password == null) {throw new RuntimeException("Username or password is missing in common.properties");
        }

        LoginPom login = new LoginPom(driver);
        login.getUsername(username);
        login.getPassword(password);
        login.getLoginButton();

        Reporter.log("Logged in successfully and navigated to Product Page", true);
    }

    @AfterMethod
    public void closeApplication() {

        System.out.println("Quit browser");

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}