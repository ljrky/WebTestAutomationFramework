package com.testframework.base.Trello;

import com.testframework.base.Utils.WebDriverHelper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElementEnabled;
import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElementToBeVisible;

public class LoginPage {

    private WebDriver driver;

   @FindBy(how = How.ID, using = "user")
    public static WebElement Username;

    @FindBy(how = How.ID, using = "password")
    public static WebElement Password;

    @FindBy(how = How.ID, using = "login")
    public static WebElement Login;

    @FindBy(how = How.ID, using = "content")
    public static WebElement MyBoards;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }

    public void enterEmail(String email) {
        Username.clear();
        Username.sendKeys(email);
    }

    public void enterPassword(String password) {
        Password.clear();
        Password.sendKeys(password);
    }

    public void clickLogin() {
        Login.click();
    }

    public void Login(String Email, String Password) {
        WaitForElementEnabled(Username);
        enterEmail(Email);
        enterPassword(Password);
        clickLogin();
        WaitForElementEnabled(MyBoards);
    }

}
