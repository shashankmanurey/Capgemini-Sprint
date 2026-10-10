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
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
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

    @BeforeSuite(alwaysRun = true)
    public void Bs() {
        System.out.println("Open Database connectivity");
    }

    @AfterSuite(alwaysRun = true)
    public void As() {
        System.out.println("Close Database connectivity");
    }

    @BeforeTest(alwaysRun = true)
    public void Bt() {
        System.out.println("Pre-conditions");
    }

    @AfterTest(alwaysRun = true)
    public void At() {
        System.out.println("Post-conditions");
    }

    @BeforeMethod(alwaysRun = true)
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
 
        	Map<String, Object> edgePrefs = new HashMap<>();
            edgePrefs.put("credentials_enable_service", false);
            edgePrefs.put("profile.password_manager_enabled", false);
            edgePrefs.put("profile.password_manager_leak_detection", false);

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.setExperimentalOption("prefs", edgePrefs);

            driver = new EdgeDriver(edgeOptions);

        } else if (browser.equalsIgnoreCase("firefox")) {
        	
        	FirefoxOptions firefoxOptions = new FirefoxOptions();

            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("signon.rememberSignons", false);
            profile.setPreference("signon.autofillForms", false);

            firefoxOptions.setProfile(profile);

            driver = new FirefoxDriver(firefoxOptions);
        }
        
        else {

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

        boolean loggedIn = login.isProductsPageDisplayed();

        if (!loggedIn) 
        {
            throw new RuntimeException("Login failed: Products page was not displayed. Current URL: "+ driver.getCurrentUrl());
        }
        Reporter.log("Logged in successfully and navigated to Product Page", true);
    }

    @AfterMethod(alwaysRun = true)
    public void closeApplication() {

        System.out.println("Quit browser");

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}