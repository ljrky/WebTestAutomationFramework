package com.testframework.base.BaseTestCase;

import com.testframework.base.Utils.WebDriverhelper.WaitForLoad;
import com.testframework.base.Wallet.CheckOut.SignIn;
import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;
import static com.testframework.base.Utils.WebDriverhelper.WaitForLoad.WaitForLogoutPageToLoad;
import static com.testframework.base.Utils.WebDriverhelper.WaitForLoad.WaitForPageToLoad;

/**
 * Created by kerua on 7/9/2014.
 */
public class SimpleTestCase {
    private SelendroidLauncher selendroidServer = null;
    protected WebDriver driver;
    protected String HomePage, BrowserType, waitForElementTimeout, logoutURL, IEProperty, IEDriver, ChromeProperty, ChromeDriver, WPRemoteDriverURL, FireFoxProperty, FireFoxDriver;

    @BeforeClass()
    public void init() {
        this.HomePage = System.getProperty("HomePage");
        this.BrowserType = System.getProperty("Browser");
        WaitForLoad.waitForElementTimeout = Integer.parseInt(System.getProperty("waitForElementTimeout"));
        initConfigurationVariables();

        //TODO : add log system here
        //Launch browser
        launchBrowser(BrowserType);
        //Set implicitly wait for all element
        WaitForLoad.SetImplicitlyWaitForAllElement(driver);
        //Direct to Logout Page
        driver.get(HomePage + logoutURL);
        WaitForLogoutPageToLoad(driver);
    }

    @AfterClass
    public void stop() {
        //Direct to Logout Page
        driver.get(HomePage + logoutURL);
        WaitForLogoutPageToLoad(driver);
        //Close Browser
        driver.quit();
    }

    private void launchBrowser(String browserType){
        switch(browserType){
            case "IE" :
                System.setProperty(IEProperty, IEDriver);
                driver = new InternetExplorerDriver();
                break;
            case "Chrome":
                System.setProperty(ChromeProperty, ChromeDriver);
                driver = new ChromeDriver();
                break;
            case "FireFox":
                System.setProperty(FireFoxProperty, FireFoxDriver);
                driver = new FirefoxDriver();
                break;
            case "Android":
                if (selendroidServer != null) {
                    selendroidServer.stopSelendroid();
                }
                SelendroidConfiguration config = new SelendroidConfiguration();
                selendroidServer = new SelendroidLauncher(config);
                selendroidServer.lauchSelendroid();
                DesiredCapabilities caps = SelendroidCapabilities.android();
                try {
                    driver = new SelendroidDriver(caps);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "WP":
                DesiredCapabilities capWP = DesiredCapabilities.internetExplorer();
                try {
                    driver = new RemoteWebDriver(new URL(WPRemoteDriverURL), capWP);
                }
                catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }
    }

    private void initConfigurationVariables() {
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.base.BaseTestCase.SimpleTestCase");
        IEProperty = resourceBundle.getString("IEProperty");
        IEDriver = resourceBundle.getString("IEDriver");
        ChromeProperty = resourceBundle.getString("ChromeProperty");
        ChromeDriver = resourceBundle.getString("ChromeDriver");
        FireFoxProperty = resourceBundle.getString("FireFoxProperty");
        FireFoxDriver = resourceBundle.getString("FireFoxDriver");
        WPRemoteDriverURL = resourceBundle.getString("WPRemoteDriverURL");
        logoutURL = resourceBundle.getString("logoutURL");
    }
}