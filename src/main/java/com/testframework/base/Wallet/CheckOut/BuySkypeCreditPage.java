package com.testframework.base.Wallet.CheckOut;

import com.testframework.base.Utils.WebDriverHelper.SelectHelper;
import com.testframework.base.Utils.WebDriverHelper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElementToBeClickByID;

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

    @FindBy(how = How.ID, using = "totalPrice")
    public static WebElement Total;

    @FindBy(how = How.ID, using = "productPrice")
    public static WebElement SkypeCreditPrice;

    @FindBy(how = How.ID, using = "container")
    public static WebElement SkypeCreditForm;

    public BuySkypeCreditPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
        WaitHelper.WaitForElement(Currency);
    }

    public void clickContinue() {
        SkypeCreditForm.click();
        WaitHelper.WaitForElement(Continue);
        WaitForElementToBeClickByID(driver, "productSelection");
        Continue.click();
    }

    public void DisableAutoRecharge() {
        WaitHelper.WaitForElement(EnableAutoRecharge);
        if(EnableAutoRecharge.isSelected()){
            EnableAutoRecharge.click();
        }
    }

    public void EnableAutoRecharge() {
        WaitHelper.WaitForElement(EnableAutoRecharge);
        if(EnableAutoRecharge.isSelected() != true){
            EnableAutoRecharge.click();
        }
    }

    public void selectCurrency(String CurrencyType) {
        WaitHelper.WaitForElement(Currency);
        SelectHelper.selectByValue(Currency, CurrencyType);
        WaitHelper.WaitForElement(SkypeCreditPrice);
        WaitHelper.WaitForElement(Total);
    }

    public void ContinueWithDefaultProduct() {
        selectCurrency("EUR");
        clickContinue();
    }
}
