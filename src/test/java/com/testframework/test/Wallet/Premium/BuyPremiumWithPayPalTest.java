package com.testframework.test.Wallet.Premium;

import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.Wallet.CheckOut.OrderConfirmationPage;
import com.testframework.base.Wallet.CheckOut.PaymentMethodPage;
import com.testframework.base.Wallet.CheckOut.SignInPage;
import com.testframework.base.Wallet.Partner.PayPal.PayPalLogin;
import com.testframework.base.Wallet.Premium.PremiumPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;

/**
 * Created by kerua on 7/21/2014.
 */
public class BuyPremiumWithPayPalTest extends SimpleTestCase{
    public String skypeName, Email, Password, premiumURL;
    private HashMap<String, String> PayPayAccount;

    @BeforeClass
    public void initVariables(){
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.Wallet.Premium.BuyPremiumWithPayPalTest");
        skypeName = resourceBundle.getString("skypeName");
        premiumURL = resourceBundle.getString("premiumURL");
        Email = resourceBundle.getString("Email");
        Password = resourceBundle.getString("Password");
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
        driver.get(HomePage + premiumURL);
        PremiumPage premiumPage = new PremiumPage(driver);
        premiumPage.ChooseUnlimitedPackage();
    }

    @Test()
    public void PremiumTest() {

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
