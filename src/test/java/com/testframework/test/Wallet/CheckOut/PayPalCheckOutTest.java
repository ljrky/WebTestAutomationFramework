package com.testframework.test.Wallet.CheckOut;

import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.Wallet.CheckOut.BuySkypeCreditPage;
import com.testframework.base.Wallet.CheckOut.OrderConfirmationPage;
import com.testframework.base.Wallet.CheckOut.PaymentMethodPage;
import com.testframework.base.Wallet.CheckOut.SignInPage;
import com.testframework.base.Wallet.Partner.PayPal.PayPalLogin;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;

/**
 * Created by kerua on 7/17/2014.
 */
public class PayPalCheckOutTest extends SimpleTestCase{

    private String skypeName, Email, Password, buyCreditURL;
    private HashMap<String, String> PayPayAccount;

    @BeforeClass
    public void initVariables(){
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.Wallet.CheckOut.PayPalCheckOutTest");
        skypeName = resourceBundle.getString("skypeName");
        Email = resourceBundle.getString("Email");
        Password = resourceBundle.getString("Password");
        buyCreditURL = resourceBundle.getString("buyCreditURL");
        PayPayAccount = new HashMap<String, String>(){
            {
                put("Email", Email);
                put("Password", Password);
            }
        };
    }

    @BeforeMethod
    public void beforeMethod(){
        //Login Function
        SignInPage signInPage = new SignInPage(driver);
        signInPage.Login(skypeName);

        //Redirect
        driver.get(HomePage + buyCreditURL);
        BuySkypeCreditPage skypecreditPage = new BuySkypeCreditPage(driver);
        skypecreditPage.ContinueWithDefaultProduct();
    }

    @Test()
    public void HomePage() {
    	PaymentMethodPage paymentMethodPage = new PaymentMethodPage(driver);
        paymentMethodPage.UseNewPaymentMethod();
        paymentMethodPage.selectPaymentMethod("PAYPAL");
	    paymentMethodPage.clickContinue();

        PayPalLogin payPalLogin = new PayPalLogin(driver);
        payPalLogin.SignInWithPayPalAccount(PayPayAccount);
        payPalLogin.Pay();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.verifyOrderPlaced();
    }
}
