package com.testframework.base.Wallet.CheckOut;

import com.testframework.base.Utils.WebDriverhelper.WaitForLoad;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import static com.testframework.base.Utils.WebDriverhelper.WaitForLoad.WaitForElementToBeClickByID;
import static com.testframework.base.Utils.WebDriverhelper.WaitForLoad.WaitForElementToBeVisibleByID;

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

    @FindBy(how = How.ID, using = "label-checkbox-autoRecharge")
    public static WebElement EnableAutoRecharge;

    @FindBy(how = How.ID, using = "loader")
    public static WebElement Loader;

    public BuySkypeCreditPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
        WaitForLoad.WaitForElement(Currency);
    }

    public void clickContinue() {
        WaitForLoad.WaitForElement(Continue);
        WaitForElementToBeClickByID(driver, "productSelection");
        Continue.click();
    }

    public void DisableAutoRecharge() {
        WaitForLoad.WaitForElement(EnableAutoRecharge);
        if(EnableAutoRecharge.isSelected()){
            EnableAutoRecharge.click();
        }
    }

    public void EnableAutoRecharge() {
        WaitForLoad.WaitForElement(EnableAutoRecharge);
        if(EnableAutoRecharge.isSelected() != true){
            EnableAutoRecharge.click();
        }
    }

    public void selectCurrency(String CurrencyType) {
        WaitForLoad.WaitForElement(Currency);
        Select realSelect = new Select(Currency);
        realSelect.selectByValue(CurrencyType);
    }

    public void ContinueWithDefaultProduct() {
        selectCurrency("EUR");
        WaitForLoad.WaitForInvisibilityOfElementByID(driver,"loader");
        clickContinue();
    }
}
