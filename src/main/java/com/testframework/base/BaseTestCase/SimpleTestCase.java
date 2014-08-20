package com.testframework.base.BaseTestCase;

import com.testframework.base.Utils.WebDriverHelper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;
/**
 * Created by kerua on 7/9/2014.
 */
public class SimpleTestCase {
    protected WebDriver driver;
    DesiredCapabilities capabilities;
    protected String HomePage, Browser, waitForElementTimeout,loginURL, logoutURL, IEProperty, IEDriver, ChromeProperty, ChromeDriver, WPRemoteDriverURL, FireFoxProperty, FireFoxDriver;

    @BeforeClass()
    public void init() {
        this.HomePage = System.getProperty("HomePage");
        this.Browser = System.getProperty("Browser");
        WaitHelper.waitForElementTimeout = Integer.parseInt(System.getProperty("waitForElementTimeout"));
        initConfigurationVariables();

        //Launch browser
        launchBrowser(Browser);
        //Set implicitly wait for all element
        WaitHelper.SetImplicitlyWaitForAllElement(driver);
    }

    @BeforeMethod()
    public void stopMethod() {
        //Direct to Login Page
        driver.get(HomePage + loginURL);
    }
    @AfterClass
    public void stop() {
        //Close Browser
        driver.quit();
    }

    private void launchBrowser(String browserType){
        switch(browserType){
            case "IE" :
                System.setProperty(IEProperty, IEDriver);
                driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
                break;
            case "Chrome":
                System.setProperty(ChromeProperty, ChromeDriver);
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "FireFox":
                System.setProperty(FireFoxProperty, FireFoxDriver);
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case "Safari":
                driver = new SafariDriver();
                driver.manage().window().maximize();
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
        logoutURL = resourceBundle.getString("logoutURL");
        loginURL = resourceBundle.getString("loginURL");
    }
}