package com.testframework.base.Wallet.CheckOut;

import org.openqa.selenium.JavascriptExecutor;
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
public class CheckoutPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "continueButton")
    public static WebElement ContinueButton;

    @FindBy(how = How.ID, using = "cancelFlow")
    public static WebElement CancelButton;

    @FindBy(how = How.LINK_TEXT, using = "Use a new payment method")
    public static WebElement UseNewPaymentMethod;

    @FindBy(how = How.LINK_TEXT, using = "Pay now")
    public static WebElement PayNow;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }


    public void clickTOS() {
//		WaitForPageToLoad
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

    public void UseNewPaymentMethod() {
        UseNewPaymentMethod.click();
    }

    public void UseStoredPaymentMethod() {
        PayNow.click();
    }

}
