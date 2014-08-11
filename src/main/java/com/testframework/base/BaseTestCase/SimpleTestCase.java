package com.testframework.base.BaseTestCase;

import com.testframework.base.Utils.WebDriverHelper.WaitHelper;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;
import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForLogoutPageToLoad;

/**
 * Created by kerua on 7/9/2014.
 */
public class SimpleTestCase {
    protected WebDriver driver;
    DesiredCapabilities capabilities;
    protected String HomePage, BrowserType, waitForElementTimeout, logoutURL, IEProperty, IEDriver, ChromeProperty, ChromeDriver, WPRemoteDriverURL, FireFoxProperty, FireFoxDriver;

    @BeforeClass()
    public void init() {
        this.HomePage = System.getProperty("HomePage");
        this.BrowserType = System.getProperty("Browser");
        WaitHelper.waitForElementTimeout = Integer.parseInt(System.getProperty("waitForElementTimeout"));
        initConfigurationVariables();

        //TODO : add log system here
        //Launch browser
        launchBrowser(BrowserType);
        //Set implicitly wait for all element
        WaitHelper.SetImplicitlyWaitForAllElement(driver);
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
            case "Android":
                // set up appium
                capabilities = new DesiredCapabilities();
                // Configuration for device name
                capabilities.setCapability("deviceName", "S3");
                // Configuration for using chrome browser in device
                capabilities.setCapability("browserName", "Chrome");
                // Configuration for Android version on device
                capabilities.setCapability("platformVersion", "4.3");
                // Configuration for setting Appnium to execute on Andorid
                capabilities.setCapability("platformName", "Android");
                try {
                    // URL of Appium Server
                    driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
                    Thread.sleep(5000);
                    driver.get("https://qasecure.skype.net");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case "iOS":
                // set up appium
                capabilities = new DesiredCapabilities();
                // Configuration for device name
                capabilities.setCapability("deviceName", "iPad");
                // Configuration for using Safari browser in device
                capabilities.setCapability("browserName", "Safari");
                // Configuration for iOS version on device
                capabilities.setCapability("platformVersion", "7.1");
                // Configuration for setting Appnium to execute on iOS
                capabilities.setCapability("platformName", "iOS");
                try {
                    // URL of Appium Server
                    driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
                    Thread.sleep(5000);
                    driver.get("https://qasecure.skype.net");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
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