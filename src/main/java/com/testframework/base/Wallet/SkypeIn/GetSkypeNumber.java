package com.testframework.base.Wallet.SkypeIn;

import com.testframework.base.Utils.WebDriverHelper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForPageToLoad;

/**
 * Created by kerua on 8/11/2014.
 */
public class GetSkypeNumber {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "btnBuyNumber")
    public static WebElement Continue;

    @FindBy(how = How.ID, using = "lnkSelectNumber0")
    public static WebElement FirstSuggestedNumber;

    @FindBy(how = How.ID, using = "local-area-select")
    public static WebElement SelectAreaCode;

    public GetSkypeNumber(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
    }

    public void ChooseNumber() {
        WaitHelper.WaitForElement(SelectAreaCode);
        SelectAreaCode();
        WaitHelper.WaitForElement(FirstSuggestedNumber);
        FirstSuggestedNumber.click();
        WaitHelper.WaitForElement(Continue);
        Continue.click();
    }

    public void SelectAreaCode() {
        Select realSelect = new Select(SelectAreaCode);
        realSelect.selectByIndex(0);
    }
}
