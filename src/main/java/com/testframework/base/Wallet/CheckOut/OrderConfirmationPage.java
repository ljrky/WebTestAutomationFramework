package com.testframework.base.Wallet.CheckOut;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import static com.testframework.base.Utils.WebDriverhelper.WaitForLoad.WaitForSuccess;

/**
 * Created by kerua on 7/21/2014.
 */
public class OrderConfirmationPage {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "continueButton")
    public static WebElement ContinueButton;


    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }


    public void verifyOrderPlaced() {
        WaitForSuccess(driver);
    }
}
