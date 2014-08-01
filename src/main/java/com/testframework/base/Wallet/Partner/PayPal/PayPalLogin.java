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
public class PayPalLogin {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "login_email")
    public static WebElement Email;

    @FindBy(how = How.ID, using = "login_password")
    public static WebElement Password;

    @FindBy(how = How.ID, using = "submitLogin")
    public static WebElement Login;

    @FindBy(how = How.ID, using = "continue")
    public static WebElement PayNow;

    @FindBy(how = How.ID, using = "footerSandbox")
    public static WebElement Footer;


    public PayPalLogin(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
    }

    public void SignInWithPayPalAccount(HashMap<String, String> PayPayAccount) {
        WaitHelper.WaitForElement(Email);
        Email.clear();
        Email.sendKeys(PayPayAccount.get("Email"));
        Password.clear();
        Password.sendKeys(PayPayAccount.get("Password"));
        Login.click();
        WaitHelper.WaitForElement(PayNow);
    }

    public void Pay() {
        WaitHelper.WaitForElement(Footer);
        PayNow.click();
        WaitHelper.WaitForElement(PayNow);
    }

}
