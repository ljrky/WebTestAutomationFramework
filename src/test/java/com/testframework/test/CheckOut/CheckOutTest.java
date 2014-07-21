package com.testframework.test.CheckOut;

import com.testframework.base.CheckOut.CheckoutPage;
import com.testframework.base.CheckOut.PaymentMethodPage;
import com.testframework.base.CheckOut.SignIn;
import com.testframework.base.CheckOut.SkypeCreditPage;
import com.testframework.base.BaseTestCase.SimpleTestCase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.ResourceBundle;

import static com.testframework.base.utils.testDataHelper.GetResourceBundle.getResourceBundle;

/**
 * Created by kerua on 7/17/2014.
 */
public class CheckOutTest extends SimpleTestCase{

    public String skypeName, cardNumber, nameOnCard, ExpiryMonth, ExpiryYear, cardSecurityCode;

    @BeforeClass
    public void initVariables(){
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.CheckOut.CheckOutTest");
        skypeName = resourceBundle.getString("skypeName");
        cardNumber = resourceBundle.getString("cardNumber");
        nameOnCard = resourceBundle.getString("nameOnCard");
        ExpiryMonth = resourceBundle.getString("ExpiryMonth");
        ExpiryYear = resourceBundle.getString("ExpiryYear");
        cardSecurityCode = resourceBundle.getString("cardSecurityCode");
    }

    @BeforeMethod
    public void beforeMethod(){
        SkypeCreditPage skypecreditPage = new SkypeCreditPage(driver);
        skypecreditPage.clickContinue();
        //Login Function
        SignIn signIn = new SignIn(driver);
        signIn.Login(skypeName);
    }

    @Test()
    public void HomePage() {

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
    }
}
