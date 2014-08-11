package com.testframework.test.Wallet.SkypeIn;

import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.Wallet.CheckOut.OrderConfirmationPage;
import com.testframework.base.Wallet.CheckOut.PaymentMethodPage;
import com.testframework.base.Wallet.CheckOut.SignInPage;
import com.testframework.base.Wallet.Premium.PremiumPage;
import com.testframework.base.Wallet.SkypeIn.ChooseYourSubscription;
import com.testframework.base.Wallet.SkypeIn.GetSkypeNumber;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;

/**
 * Created by kerua on 8/11/2014.
 */
public class BuySkypeInWithCreditCardTest extends SimpleTestCase {
    public String skypeName, cardNumber, nameOnCard, ExpiryMonth, ExpiryYear, cardSecurityCode, skypeInURL;
    private HashMap<String, String> CardInformation;

    @BeforeClass
    public void initVariables(){
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.Wallet.SkypeIn.BuySkypeInWithCreditCardTest");
        skypeName = resourceBundle.getString("skypeName");
        cardNumber = resourceBundle.getString("cardNumber");
        nameOnCard = resourceBundle.getString("nameOnCard");
        ExpiryMonth = resourceBundle.getString("ExpiryMonth");
        ExpiryYear = resourceBundle.getString("ExpiryYear");
        cardSecurityCode = resourceBundle.getString("cardSecurityCode");
        skypeInURL = resourceBundle.getString("skypeInURL");
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
        driver.get(HomePage + skypeInURL);
        GetSkypeNumber getSkypeNumber = new GetSkypeNumber(driver);
        getSkypeNumber.ChooseNumber();

        ChooseYourSubscription chooseYourSubscription = new ChooseYourSubscription(driver);
        chooseYourSubscription.ChooseSubscription();
    }

    @Test()
    public void SkypeInTest() {
        PaymentMethodPage paymentMethodPage = new PaymentMethodPage(driver);
        paymentMethodPage.UseNewPaymentMethod();
        paymentMethodPage.selectPaymentMethod("CARD-SKYPE");
        paymentMethodPage.fillCreditCardForm(CardInformation);
        paymentMethodPage.clickPayNow();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.verifyOrderPlaced();
    }

}
