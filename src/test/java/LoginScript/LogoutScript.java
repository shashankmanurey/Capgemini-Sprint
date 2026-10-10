package LoginScript;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import PomClassUtilities.HamburgerPom;
import PomClassUtilities.LoginPom;

public class LogoutScript extends BaseClass{

	@BeforeMethod(groups = {"functionality"})
    public void loginBeforeTest() throws Exception 
    {
        loginToApplication();
    }

    //Checking if logout functionality works
    @Test(groups = {"functionality"})
    public void verifyLogOut()
    {
        HamburgerPom hamburger = new HamburgerPom(driver);
        LoginPom login = new LoginPom(driver);

        hamburger.getHam();
        hamburger.getLogout();

        Assert.assertTrue(login.isUsernameDisplayed(), "The logout functionality did not work");
        Reporter.log("The Logout Functionality is working as expected");

    }
    
}
