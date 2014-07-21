package com.testframework.base.BaseTestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.testframework.base.utils.testDataHelper.GetResourceBundle.getResourceBundle;

/**
 * Created by kerua on 7/9/2014.
 */
public class SimpleTestCase {
    protected WebDriver driver;
    protected String HomePage, BrowserType, logoutURL, IEProperty, IEDriver, ChromeProperty, ChromeDriver, WPRemoteDriverURL;

    @Parameters({ "homePage", "browserType" })
    @BeforeClass()
    public void init(String homePage, String browserType) {

        this.HomePage = homePage;
        this.BrowserType = browserType;

        initConfigurationVariables();

        //TODO : add log system here
        //Launch browser
        launchBrowser(BrowserType);
        //Direct to LogoutPage
        driver.get(HomePage + logoutURL);
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
                break;
            case "Chrome":
                System.setProperty(ChromeProperty, ChromeDriver);
                driver = new ChromeDriver();
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
        logoutURL = resourceBundle.getString("logoutURL");
        IEProperty = resourceBundle.getString("IEProperty");
        IEDriver = resourceBundle.getString("IEDriver");
        ChromeProperty = resourceBundle.getString("ChromeProperty");
        ChromeDriver = resourceBundle.getString("ChromeDriver");
        WPRemoteDriverURL = resourceBundle.getString("WPRemoteDriverURL");
    }
}