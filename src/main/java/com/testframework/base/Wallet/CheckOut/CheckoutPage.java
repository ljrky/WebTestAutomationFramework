package com.testframework.base.Wallet.CheckOut;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import static com.testframework.base.Utils.WebDriverhelper.WaitForLoad.WaitForPageToLoad;

/**
 * Created by kerua on 7/17/2014.
 */
public class CheckoutPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "continueButton")
    public static WebElement ContinueButton;

    @FindBy(how = How.ID, using = "cancelFlow")
    public static WebElement CancelButton;

    @FindBy(how = How.LINK_TEXT, using = "Use a new payment method")
    public static WebElement UseNewPaymentMethodLink;

    @FindBy(how = How.LINK_TEXT, using = "Pay now")
    public static WebElement PayNow;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }



    public void UseNewPaymentMethod() {
        WaitForPageToLoad(driver);
        UseNewPaymentMethodLink.click();
    }

    public void UseStoredPaymentMethod() {
        WaitForPageToLoad(driver);
        PayNow.click();
    }
}
