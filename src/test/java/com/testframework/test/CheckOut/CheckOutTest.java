package com.testframework.test.CheckOut;

import com.testframework.base.CheckOut.CheckoutPage;
import com.testframework.base.CheckOut.SignIn;
import com.testframework.base.CheckOut.SkypeCreditPage;
import com.testframework.base.SimpleTestCase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

import static com.testframework.base.utils.testDataHelper.GetResourceBundle.getResourceBundle;

/**
 * Created by kerua on 7/17/2014.
 */
public class CheckOutTest extends SimpleTestCase{

    public String skypeName, skypeCreditPage, cardNumber, nameOnCard, ExpiryMonth, ExpiryYear, cardSecurityCode;

    @BeforeClass
    public void initVariables(){
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.CheckOut.CheckOutTest");
        skypeName = resourceBundle.getString("skypeName");
        skypeCreditPage = resourceBundle.getString("skypeCreditPage");
        cardNumber = resourceBundle.getString("cardNumber");
        nameOnCard = resourceBundle.getString("nameOnCard");
        ExpiryMonth = resourceBundle.getString("ExpiryMonth");
        ExpiryYear = resourceBundle.getString("ExpiryYear");
        cardSecurityCode = resourceBundle.getString("cardSecurityCode");
    }

    @BeforeMethod
    public void beforeMethod(){
        //Login Function
        SignIn signIn = new SignIn(driver);
        signIn.clickSignInWithSkypeAccount();
        signIn.SingInWithSkypeAccount(skypeName, skypeCreditPage);
        SkypeCreditPage skypecreditPage = new SkypeCreditPage(driver);
        skypecreditPage.clickContinue();
    }

    @Test()
    public void HomePage() {

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickTOS();

//	    Reporter.log("Test start\n", 1);
//	    Reporter.log("1) Navigate to Homepage; \n", 1);
//		IndexPage indexpage = new IndexPage(driver);
//	    WaitForLoad.WaitForElement(com.skype.wallet.IndexPage.link25USDCreditATU,5000, 5);
//	    indexpage.clicklink25USDCreditATU();
//
//		HomePage homepage = new HomePage(driver);
//		Reporter.log("2) Filling Card information; \n", 1);
//		String[] CardInformation = {cardNumber, nameOnCard, ExpiryMonth, ExpiryYear, cardSecurityCode};
//		homepage.fillCreditCardForm(CardInformation);
//
//	    Reporter.log("3) Click Pay now; \n", 1);
//	    homepage.clickPayNow();


    }
}
