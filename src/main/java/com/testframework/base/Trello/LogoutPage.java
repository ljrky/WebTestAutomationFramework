package com.testframework.base.Trello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElementEnabled;
import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElementToBeVisible;

public class LogoutPage {

    private WebDriver driver;

    @FindBy(how = How.LINK_TEXT, using = "Log In")
    public static WebElement Login;

    @FindBy(how = How.LINK_TEXT, using = "Sign Up")
    public static WebElement SignUp;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }

    public void LogoutLoaded() {
        WaitForElementToBeVisible(driver, Login);
        WaitForElementToBeVisible(driver, SignUp);
    }

}
