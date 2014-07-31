package com.testframework.base.Wallet.SkypeHomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Created by kerua on 7/22/2014.
 */
public class MyAccountPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "scom")
    public static WebElement Header;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }

//    public void waitForMyAccountPage(){
//        WaitForPageToLoad(driver);
//        WaitForElementToBeVisible(driver,Header);
//    }
}
