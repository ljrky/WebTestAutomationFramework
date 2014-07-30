package com.testframework.base.Wallet.CheckOut;

import com.testframework.base.Utils.WebDriverhelper.WaitForLoad;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by kerua on 7/17/2014.
 */
public class BuySkypeCreditPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "productSelection")
    public static WebElement Continue;

    @FindBy(how = How.CLASS_NAME, using = "cancelFlow")
    public static WebElement Cancel;

    @FindBy(how = How.CLASS_NAME, using = "customSelectElement")
    public static WebElement Currency;

    public BuySkypeCreditPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
        WaitForLoad.WaitForElement(Currency);
        selectCurrency();
    }

    public void clickContinue() {
        Continue.click();
    }

    public void selectCurrency() {
        Select realSelect = new Select(Currency);
        realSelect.selectByValue("EUR");
    }

    public void ContinueWithDefaultProduct() {
        clickContinue();
    }
}
