package com.testframework.base.Utils.WebDriverHelper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElement;

/**
 * Created by kerua on 7/31/2014.
 */
public class SelectHelper {

    public static void selectByValue(WebElement Element, String value) {
        WaitForElement(Element);
        Select SelectElement = new Select(Element);
        SelectElement.selectByValue(value);
    }
}
