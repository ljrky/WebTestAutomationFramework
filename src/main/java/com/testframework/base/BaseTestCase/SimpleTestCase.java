package com.testframework.base.BaseTestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.ResourceBundle;

import static com.testframework.base.utils.testDataHelper.GetResourceBundle.getResourceBundle;

/**
 * Created by kerua on 7/9/2014.
 */
public class SimpleTestCase {
    protected WebDriver driver;
    protected String HomePage, BrowserType, logoutURL, buyCreditURL;

    @Parameters({ "homePage", "browserType" })
    @BeforeClass()
    public void init(String homePage, String browserType) {

        this.HomePage = homePage;
        this.BrowserType = browserType;

        ResourceBundle resourceBundle = getResourceBundle("com.testframework.base.BaseTestCase.SimpleTestCase");
        logoutURL = resourceBundle.getString("logoutURL");
        buyCreditURL = resourceBundle.getString("buyCreditURL");

        //TODO : add log system here
        //Launch browser
        launchBrowser(BrowserType);
        //Direct to HomePage
        driver.get(HomePage + buyCreditURL);
    }

    @AfterClass
    public void stop() {
        //Close Browser
        driver.quit();
    }

    private void launchBrowser(String browserType){
        switch(browserType){
            case "IE" :
                System.setProperty("webdriver.ie.driver", "BrowserDriver/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "BrowserDriver/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }
    }
}