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

import java.util.HashMap;

import static com.testframework.base.Utils.WebDriverhelper.WaitForLoad.WaitForPageToLoad;

/**
 * Created by kerua on 7/25/2014.
 */
public class BillingAddressPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "firstName")
    public static WebElement firstName;

    @FindBy(how = How.ID, using = "lastName")
    public static WebElement lastName;

    @FindBy(how = How.ID, using = "street")
    public static WebElement address;

    @FindBy(how = How.ID, using = "city")
    public static WebElement city;

    @FindBy(how = How.ID, using = "zip")
    public static WebElement zip;

    @FindBy(how = How.ID, using = "continueButton")
    public static WebElement ContinueButton;

    @FindBy(how = How.ID, using = "cancelFlow")
    public static WebElement CancelButton;

    @FindBy(how = How.ID, using = "billingInformation")
    public static WebElement BillingAddressForm;

    @FindBy(how = How.ID, using = "billingCountry")
    public static WebElement billingCountry;


    public BillingAddressPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
    }

    public void clickContinue() {
        WaitForLoad.WaitForElement(ContinueButton);
        ContinueButton.click();
    }


    public void fillBillingAddress(HashMap<String, String> CardInformation) {
        WaitForPageToLoad(BillingAddressForm);
        firstName.sendKeys(CardInformation.get("nameOnCard"));
        lastName.sendKeys(CardInformation.get("nameOnCard"));
        address.sendKeys(CardInformation.get("nameOnCard"));
        city.sendKeys(CardInformation.get("nameOnCard"));
        zip.sendKeys(CardInformation.get("nameOnCard"));
        Select realSelect = new Select(billingCountry);
        realSelect.selectByValue("DE");
        ContinueWithDefaultProduct();
    }

    public void ContinueWithDefaultProduct() {
        clickContinue();
    }
}
