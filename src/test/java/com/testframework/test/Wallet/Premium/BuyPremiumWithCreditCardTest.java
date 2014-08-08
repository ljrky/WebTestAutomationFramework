package com.testframework.test.Wallet.Premium;

import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.Wallet.CheckOut.*;
import com.testframework.base.Wallet.Premium.PremiumPage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;

/**
 * Created by kerua on 7/21/2014.
 */
public class BuyPremiumWithCreditCardTest extends SimpleTestCase{
    public String skypeName, cardNumber, nameOnCard, ExpiryMonth, ExpiryYear, cardSecurityCode, premiumURL;
    private HashMap<String, String> CardInformation;

    @BeforeClass
    public void initVariables(){
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.Wallet.Premium.BuyPremiumWithCreditCardTest");
        skypeName = resourceBundle.getString("skypeName");
        cardNumber = resourceBundle.getString("cardNumber");
        nameOnCard = resourceBundle.getString("nameOnCard");
        ExpiryMonth = resourceBundle.getString("ExpiryMonth");
        ExpiryYear = resourceBundle.getString("ExpiryYear");
        cardSecurityCode = resourceBundle.getString("cardSecurityCode");
        premiumURL = resourceBundle.getString("premiumURL");
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
        //Login Function
        SignInPage signInPage = new SignInPage(driver);
        signInPage.Login(skypeName);

        //Redirect
        driver.get(HomePage + premiumURL);
        PremiumPage premiumPage = new PremiumPage(driver);
        premiumPage.ChooseUnlimitedPackage();
    }

    @Test()
    public void PremiumTest() {
        PaymentMethodPage paymentMethodPage = new PaymentMethodPage(driver);
        paymentMethodPage.UseNewPaymentMethod();
        paymentMethodPage.selectPaymentMethod("CARD-SKYPE");
        paymentMethodPage.fillCreditCardForm(CardInformation);
        paymentMethodPage.clickPayNow();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.verifyOrderPlaced();
    }
}
