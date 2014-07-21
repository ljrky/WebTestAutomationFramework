package com.testframework.base.Wallet.CheckOut;

import com.testframework.base.utils.webhelper.WaitForLoad;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.testframework.base.utils.webhelper.WaitForLoad.WaitForElement;

/**
 * Created by kerua on 7/21/2014.
 */
public class PaymentMethodPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "cardNumber")
    public static WebElement cardNumber;

    @FindBy(how = How.ID, using = "nameOnCard")
    public static WebElement nameOnCard;

    @FindBy(how = How.NAME, using = "expiry_date_month")
    public static WebElement expiryDateMonth;

    @FindBy(how = How.NAME, using = "expiry_date_year")
    public static WebElement expiryDateYear;

    @FindBy(how = How.ID, using = "cvcInput")
    public static WebElement cardSecurityCode;

    @FindBy(how = How.ID, using = "continueButton")
    public static WebElement payNow;

    @FindBy(how = How.ID, using = "cancelFlow")
    public static WebElement cancel;

    @FindBy(how = How.CLASS_NAME, using = "skypePaymentFrame")
    public static WebElement iFrame;

    public PaymentMethodPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }

    public void enterCardNumber(String testData_CardNumber) {
        cardNumber.click();
        cardNumber.sendKeys(testData_CardNumber);
    }

    public void enterNameOnCard(String testData_NameOnCard) {
        nameOnCard.click();
        nameOnCard.sendKeys(testData_NameOnCard);
    }

    public void selectExpiryMonth(String testData_ExpiryMonth) {
//        expiryDateMonth.sendKeys(testData_ExpiryMonth);
        Select realSelect = new Select(expiryDateMonth);
        realSelect.selectByValue(testData_ExpiryMonth);
    }

//    public void selectExpiryMonthInIOS(String testData_ExpiryMonth) {
//        RemoteUIAPickerWheel yearWheel = ((RemoteUIAPickerWheel) expiryDateMonth);
//        yearWheel.select(testData_ExpiryMonth);
//    }

    public void selectExpiryYear(String testData_ExpiryYear) {
//        expiryDateYear.sendKeys(testData_ExpiryYear);
        Select realSelect = new Select(expiryDateYear);
        realSelect.selectByValue(testData_ExpiryYear);
    }

    public void enterCardSecurityCode(String testData_CardSecurityCode) {
        cardSecurityCode.click();
        cardSecurityCode.sendKeys(testData_CardSecurityCode);
    }

    public void clickPayNow() {
        payNow.click();
    }

    public void selectIframe() {
        driver.switchTo().frame(iFrame);
    }

    public void deselectIframe() {
        driver.switchTo().defaultContent();
    }

    public void clickTOS() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String executionScript = "return $(" + "\"input[name=agreeTos]:visible, input[name=termsOfService]:visible\"" + ").length;";
        Long numOfTOS = (Long) (executor.executeScript(executionScript));

        for(int i = 0; i < numOfTOS; i++){
            String isSelectScript = "return $(" + "\"input[name=agreeTos]:visible, input[name=termsOfService]:visible\"" + ")[" + i + "].checked";
            Boolean tosSelect = (Boolean)executor.executeScript(isSelectScript);

            if(tosSelect == false){
                String script = "$(" + "\"input[name=agreeTos]:visible, input[name=termsOfService]:visible\"" + ")[" + i + "].click();";
                executor.executeScript(script);
            }
        }
    }


    public void fillCreditCardForm(HashMap<String, String> CardInformation) {
        clickTOS();
        selectIframe();
        enterCardNumber(CardInformation.get("cardNumber"));
        enterNameOnCard(CardInformation.get("nameOnCard"));
        selectExpiryMonth(CardInformation.get("ExpiryMonth"));
        selectExpiryYear(CardInformation.get("ExpiryYear"));
        enterCardSecurityCode(CardInformation.get("cardSecurityCode"));
        nameOnCard.click();
        deselectIframe();
    }
}
