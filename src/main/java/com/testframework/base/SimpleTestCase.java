package com.testframework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * Created by kerua on 7/9/2014.
 */
public class SimpleTestCase {
    protected WebDriver driver;

    @Parameters({ "homePage", "browserType" })
    @BeforeClass()
    public void beforeClass(String homePage, String browserType) {

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
        driver.get(homePage);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
