package com.testframework.test.Wallet.Premium;

import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.Wallet.CheckOut.CheckoutPage;
import com.testframework.base.Wallet.CheckOut.OrderConfirmationPage;
import com.testframework.base.Wallet.CheckOut.PaymentMethodPage;
import com.testframework.base.Wallet.CheckOut.SignInPage;
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
public class BuyPremiumWithSkypeCreditTest extends SimpleTestCase{
    public String skypeName, premiumURL;

    @BeforeClass
    public void initVariables(){
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.Wallet.Premium.BuyPremiumWithSkypeCreditTest");
        skypeName = resourceBundle.getString("skypeName");
        premiumURL = resourceBundle.getString("premiumURL");
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
        paymentMethodPage.selectPaymentMethod("SMODE");
        paymentMethodPage.clickContinue();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.verifyOrderPlaced();
    }
}
