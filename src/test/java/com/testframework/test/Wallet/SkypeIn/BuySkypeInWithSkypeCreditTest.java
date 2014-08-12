package com.testframework.test.Wallet.SkypeIn;

import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.Wallet.CheckOut.OrderConfirmationPage;
import com.testframework.base.Wallet.CheckOut.PaymentMethodPage;
import com.testframework.base.Wallet.CheckOut.SignInPage;
import com.testframework.base.Wallet.Partner.PayPal.PayPalLogin;
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
public class BuySkypeInWithSkypeCreditTest extends SimpleTestCase {
    public String skypeName, skypeInURL;

    @BeforeClass
    public void initVariables(){
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.Wallet.SkypeIn.BuySkypeInWithSkypeCreditTest");
        skypeName = resourceBundle.getString("skypeName");
        skypeInURL = resourceBundle.getString("skypeInURL");
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
        paymentMethodPage.selectPaymentMethod("SMODE");
        paymentMethodPage.clickContinue();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.verifyOrderPlaced();
    }

}
