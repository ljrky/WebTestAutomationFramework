package com.testframework.base.Utils.WebDriverHelper;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by kerua on 7/9/2014.
 */
public class WaitHelper {
    static int numberOfWait = 10;
    static int waitForTransactionSuccess = 120;
    public static int waitForElementTimeout = 30;
    static int timeOut = waitForElementTimeout / 10;


    public static void SetImplicitlyWaitForAllElement(WebDriver driver) {
        System.out.println("waitForElementTimeout is set to : " + waitForElementTimeout);
        System.out.println("timeOut is set to : " + timeOut);
        driver.manage().timeouts().implicitlyWait(waitForElementTimeout, TimeUnit.SECONDS);
    }

    public static void WaitForElement(WebElement Element, int timeOut, int numberOfWait){
        for (int i = 0; i < numberOfWait; i++){
            try {
                if (Element.isDisplayed()){
                    break;
                }
                Thread.sleep(timeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void WaitForElement(WebElement Element){
        for (int i = 0; i < numberOfWait; i++){
            try {
                if (Element.isDisplayed()){
                    break;
                }
                Thread.sleep(timeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void WaitForElementEnabled(WebElement Element){
        for (int i = 0; i < numberOfWait; i++){
            try {
                if (Element.isEnabled()){
                    break;
                }
                Thread.sleep(timeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void WaitForElementToBeInvisible(WebElement Element){
        for (int i = 0; i < numberOfWait; i++){
            try {
                if (Element.isDisplayed() == false){
                    break;
                }
                Thread.sleep(timeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void WaitForLogoutPageToLoad(WebDriver driver){
        for (int i = 0; i < numberOfWait; i++){
            try {
                if (driver.findElement(By.className("bottom")).isDisplayed()){
                    break;
                }
                Thread.sleep(timeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void WaitForSuccess(WebDriver driver){
        new WebDriverWait(driver, waitForTransactionSuccess).until(
                ExpectedConditions.visibilityOfElementLocated(By.className("successful"))
        );
    }

    private static Function<WebDriver, Boolean> isPageLoaded() {
        return new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
    }

    public static void WaitForPageToLoad(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(isPageLoaded());
    }

    private static Function<WebDriver, Boolean> haveMoreThanOneOption(final By element) {
        return new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                WebElement webElement = driver.findElement(element);
                if (webElement == null) {
                    return false;
                } else {
                    int size = webElement.findElements(By.id("option")).size();
                    return size >= 1;
                }
            }
        };
    }

    public static void waitForDropDownListLoaded(WebDriver driver, final By element) {
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(haveMoreThanOneOption(element));
    }


    public static void WaitForElementToBeClickByID(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
    }

    public static void WaitForElementToBeClickable(WebDriver driver, WebElement Element){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(Element));
    }

    public static void WaitForElementToBePresenceByID(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
    }

    public static void WaitForElementToBePresenceByClassName(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
    }

    public static void WaitForInvisibilityOfElementByID(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));
    }

    public static void WaitForElementToBeVisibleByID(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
    }

    public static void WaitForFrameAndSwitchTo(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public static void WaitWithOutExcpetion(WebDriver driver, By by){
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(waitForElementTimeout,TimeUnit.SECONDS).pollingEvery(10,TimeUnit.MILLISECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static boolean IsElementExistByID(WebDriver driver, String Locator){
        try{
            driver.findElement(By.id(Locator));
            return true;
        }catch (NoSuchElementException exception){
            return false;
        }
    }

    public static void WaitForSeconds(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
