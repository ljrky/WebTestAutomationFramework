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

    @FindBy(how = How.NAME, using = "login_email")
    public static WebElement Email;

    @FindBy(how = How.NAME, using = "login_password")
    public static WebElement Password;

    @FindBy(how = How.NAME, using = "login.x")
    public static WebElement Login;

    @FindBy(how = How.ID, using = "continue")
    public static WebElement PayNow;

    public PayPalLoginMobile(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
    }

    public void SignInWithPayPalAccount(HashMap<String, String> PayPayAccount) {
        WaitHelper.WaitForSeconds(30);
        WaitHelper.WaitForElement(Email);
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
