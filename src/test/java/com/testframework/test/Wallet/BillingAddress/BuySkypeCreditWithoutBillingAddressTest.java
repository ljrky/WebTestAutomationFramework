package com.testframework.test.Wallet.BillingAddress;

import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.Wallet.CheckOut.*;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;

/**
 * Created by kerua on 7/25/2014.
 */
public class BuySkypeCreditWithoutBillingAddressTest extends SimpleTestCase {
    private String skypeName, cardNumber, nameOnCard, ExpiryMonth, ExpiryYear, cardSecurityCode, buyCreditURL;
    private HashMap<String, String> CardInformation;

    @BeforeClass
    public void initVariables(){
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.Wallet.BillingAddress.BuySkypeCreditWithoutBillingAddressTest");
        skypeName = resourceBundle.getString("skypeName");
        cardNumber = resourceBundle.getString("cardNumber");
        nameOnCard = resourceBundle.getString("nameOnCard");
        ExpiryMonth = resourceBundle.getString("ExpiryMonth");
        ExpiryYear = resourceBundle.getString("ExpiryYear");
        cardSecurityCode = resourceBundle.getString("cardSecurityCode");
        buyCreditURL = resourceBundle.getString("buyCreditURL");
        CardInformation = new HashMap<String, String>(){
            {
                put("cardNumber", cardNumber);
                put("nameOnCard", nameOnCard);
                put("ExpiryMonth", ExpiryMonth);
                put("ExpiryYear", ExpiryYear);
                put("cardSecurityCode", cardSecurityCode);
            }
        };
    }

    @BeforeMethod
    public void beforeMethod(){
        //Redirect
        driver.get(HomePage + buyCreditURL);
        BuySkypeCreditPage skypecreditPage = new BuySkypeCreditPage(driver);
        skypecreditPage.ContinueWithDefaultProduct();
        //Login Function
        SignInPage signInPage = new SignInPage(driver);
        signInPage.Login(skypeName);
    }

    @Test()
    public void TestBillingAddressPage() {
        //Need to find wait function
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BillingAddressPage billingAddressPage = new BillingAddressPage(driver);
        billingAddressPage.fillBillingAddress(CardInformation);

        //Need to find wait function
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(driver.findElements(By.linkText("Use a new payment method")).size() != 0){
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.UseNewPaymentMethod();
        }

        PaymentMethodPage paymentMethodPage = new PaymentMethodPage(driver);
        paymentMethodPage.fillCreditCardForm(CardInformation);
        paymentMethodPage.clickPayNow();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.verifyOrderPlaced();
    }
}
