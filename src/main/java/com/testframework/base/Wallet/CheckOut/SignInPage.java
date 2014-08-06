package com.testframework.base.Wallet.CheckOut;

import com.testframework.base.Utils.WebDriverHelper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Created by kerua on 7/17/2014.
 */
public class SignInPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "skypeLogin")
    public static WebElement SignInWithSkypeAccount;

    @FindBy(how = How.CLASS_NAME, using = "bottom")
    public static WebElement FooterOfThePage;

    @FindBy(how = How.ID, using = "username")
    public static WebElement Username;

    @FindBy(how = How.ID, using = "password")
    public static WebElement Password;

    @FindBy(how = How.ID, using = "signIn")
    public static WebElement SignIn;

    @FindBy(how = How.ID, using = "cancelFlow")
    public static WebElement Cancel;

    @FindBy(how = How.ID, using = "portalHeader")
    public static WebElement MyAccount;



    public SignInPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }

    public void clickSignInWithSkypeAccount() {
        WaitHelper.WaitForElement(SignInWithSkypeAccount);
        SignInWithSkypeAccount.click();
    }

    public void SignInWithSkypeAccount(String testData_UserName) {
        WaitHelper.WaitForElement(Username);
        Username.click();
        Username.sendKeys(testData_UserName);
        Password.click();
        Password.sendKeys(testData_UserName);
        SignIn.click();
        WaitHelper.WaitForElement(MyAccount);
    }

    public void Login(String testData_UserName){
        clickSignInWithSkypeAccount();
        SignInWithSkypeAccount(testData_UserName);
    }
}
