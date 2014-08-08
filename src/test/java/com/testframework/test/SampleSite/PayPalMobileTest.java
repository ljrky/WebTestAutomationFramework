package com.testframework.test.SampleSite;

import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.SampleSite.HomePage;
import com.testframework.base.Utils.WebDriverHelper.WaitHelper;
import com.testframework.base.Wallet.Partner.PayPal.PayPalLoginMobile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;
import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElement;

/**
* Created by kerua on 7/9/2014.
*/
public class PayPalMobileTest extends SimpleTestCase {

    @Test()
    public void HomePage() {
        // And now use this to visit ebay
        driver.get("http://www.paypal.com");

        // Find the text input element by its id
        WebElement element = driver.findElement(By.id("header-buttons"));

        // Enter something to search for
        WaitHelper.WaitForElement(element);
        element.click();

        // Find the text input element by its id
        WebElement user = driver.findElement(By.id("modal-email-1"));

        // Enter something to search for
        WaitHelper.WaitForElement(user);
        user.sendKeys("123");

        // Find the text input element by its id
        WebElement pass = driver.findElement(By.id("modal-password-1"));

        // Enter something to search for
        WaitHelper.WaitForElement(pass);
        pass.sendKeys("123");
        WaitHelper.WaitForSeconds(10);
        pass.submit();
        WaitHelper.WaitForSeconds(10);
    }
}
