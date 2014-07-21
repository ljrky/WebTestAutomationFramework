package com.testframework.test.Wallet.Premium;

import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.Wallet.CheckOut.*;
import com.testframework.base.Wallet.Premium.PremiumPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.ResourceBundle;

import static com.testframework.base.utils.testDataHelper.GetResourceBundle.getResourceBundle;

/**
 * Created by kerua on 7/21/2014.
 */
public class PremiumTest extends SimpleTestCase{
    public String skypeName, cardNumber, nameOnCard, ExpiryMonth, ExpiryYear, cardSecurityCode, premiumURL;

    @BeforeClass
    public void initVariables(){
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.Wallet.Premium.PremiumTest");
        skypeName = resourceBundle.getString("skypeName");
        cardNumber = resourceBundle.getString("cardNumber");
        nameOnCard = resourceBundle.getString("nameOnCard");
        ExpiryMonth = resourceBundle.getString("ExpiryMonth");
        ExpiryYear = resourceBundle.getString("ExpiryYear");
        cardSecurityCode = resourceBundle.getString("cardSecurityCode");
        premiumURL = resourceBundle.getString("premiumURL");
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get(HomePage + premiumURL);
        PremiumPage premiumPage = new PremiumPage(driver);
        premiumPage.clickUnlimitedButton();
        //Login Function
        SignIn signIn = new SignIn(driver);
        signIn.Login(skypeName);
    }

    @Test()
    public void PremiumTest() {

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickTOS();

        PaymentMethodPage paymentMethodPage = new PaymentMethodPage(driver);

        HashMap<String, String> CardInformation = new HashMap<String, String>(){
            {
                put("cardNumber", cardNumber);
                put("nameOnCard", nameOnCard);
                put("ExpiryMonth", ExpiryMonth);
                put("ExpiryYear", ExpiryYear);
                put("cardSecurityCode", cardSecurityCode);
            }
        };
        paymentMethodPage.fillCreditCardForm(CardInformation);
        paymentMethodPage.clickPayNow();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.verifyOrderPlaced();
    }
}
