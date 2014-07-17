package com.testframework.base.CheckOut;

import com.testframework.base.utils.webhelper.WaitForLoad;
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
public class SkypeCreditPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "productSelection")
    public static WebElement Continue;

    @FindBy(how = How.CLASS_NAME, using = "cancelFlow")
    public static WebElement Cancel;

    @FindBy(how = How.CLASS_NAME, using = "bottom")
    public static WebElement FooterOfThePage;

    public SkypeCreditPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
    }

    public void clickContinue() {
        WaitForLoad.WaitForPageToLoad(FooterOfThePage);
        WaitForLoad.WaitForElement(Continue);
        Continue.click();
    }
}
