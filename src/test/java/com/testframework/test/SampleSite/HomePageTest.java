package com.testframework.test.SampleSite;

import com.testframework.base.SampleSite.HomePage;
import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.Utils.WebDriverhelper.WaitForLoad;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;

/**
 * Created by kerua on 7/9/2014.
 */
public class HomePageTest extends SimpleTestCase {

    public String email, password;

    @Test()
    public void HomePage() {
        Reporter.log("Podio login test \n", 1);
        Reporter.log("1) Navigate to http://podio.com/login page; \n", 1);
        HomePage homepage = new HomePage(driver);
        Reporter.log("2) Enter valid login and password (shouldn't be hardcoded); \n", 1);
        WaitForLoad.WaitForElement(com.testframework.base.SampleSite.HomePage.emailText, 5000, 5);
        homepage.enterEmail(email);
        homepage.enterPassword(password);
        Reporter.log("3) Submit the form; \n", 1);
        homepage.submitButton();
        Reporter.log("4) Verify that you're successfully logged in; \n", 1);
        WaitForLoad.WaitForElement(com.testframework.base.SampleSite.HomePage.homeStream,5000, 5);
        homepage.homeStreamIsDisplayed();
        Reporter.log("5) Verify that posts in Activity stream block are loaded. \n", 1);
    }

    @BeforeClass
    public void initVariables(){
          ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.SampleSite.HomePageTest");
          email = resourceBundle.getString("email");
          password = resourceBundle.getString("password");
    }
}
