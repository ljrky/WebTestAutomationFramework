package com.testframework.base.Wallet.Partner.PayPal;

import com.testframework.base.Utils.WebDriverHelper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.util.HashMap;

/**
 * Created by kerua on 8/1/2014.
 */
public class PayPalLoginMobile {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "email")
    public static WebElement Email;

    @FindBy(how = How.ID, using = "password")
    public static WebElement Password;

    @FindBy(how = How.ID, using = "login")
    public static WebElement Login;

    @FindBy(how = How.ID, using = "continue")
    public static WebElement PayNow;

    @FindBy(how = How.ID, using = "frame")
    public static WebElement LoginFrame;

    public PayPalLoginMobile(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
    }

    public void SignInWithPayPalAccount(HashMap<String, String> PayPayAccount) {
        WaitHelper.WaitForSeconds(50);
        driver.switchTo().frame(LoginFrame);
        Email.clear();
        Email.sendKeys(PayPayAccount.get("Email"));
        Password.clear();
        Password.sendKeys(PayPayAccount.get("Password"));
        Login.click();
        WaitHelper.WaitForElement(PayNow);
    }

    public void Pay() {
        WaitHelper.WaitForElement(PayNow);
        PayNow.click();
        WaitHelper.WaitForElement(PayNow);
    }

}
