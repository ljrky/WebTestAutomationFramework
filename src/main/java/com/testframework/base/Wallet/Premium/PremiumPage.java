package com.testframework.base.Wallet.Premium;

import com.testframework.base.Utils.WebDriverHelper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForPageToLoad;

/**
 * Created by kerua on 7/21/2014.
 */
public class PremiumPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "purchase-premium-unlimited")
    public static WebElement UnlimitedButton;

    public PremiumPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
    }

    public void ChooseUnlimitedPackage() {
        WaitForPageToLoad(driver);
        WaitHelper.WaitForElement(UnlimitedButton);
        UnlimitedButton.click();
    }
}
