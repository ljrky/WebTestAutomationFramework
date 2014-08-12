package com.testframework.base.Wallet.SkypeIn;

import com.testframework.base.Utils.WebDriverHelper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForPageToLoad;
import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForSeconds;

/**
 * Created by kerua on 8/11/2014.
 */
public class ChooseYourSubscription {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "productSelection")
    public static WebElement Continue;

    @FindBy(how = How.ID, using = "tosCheck")
    public static WebElement TOS;

    public ChooseYourSubscription(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
    }

    public void ChooseSubscription() {
        WaitForPageToLoad(driver);
        WaitHelper.WaitForElement(TOS);
        TOS.click();
        WaitHelper.WaitForElement(Continue);
        Continue.click();
    }
}
