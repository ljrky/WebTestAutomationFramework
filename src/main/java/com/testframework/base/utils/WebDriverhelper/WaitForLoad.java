package com.testframework.base.Utils.WebDriverhelper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by kerua on 7/9/2014.
 */
public class WaitForLoad {
    static int numberOfWait = 5;
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

    public static void WaitForElementToBeEnable(WebElement Element){
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

    public static void WaitForPageToLoad(WebDriver driver){
        //need to enhance
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void WaitForElementToBeClickByID(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
    }

    public static void WaitForElementToBePresenceByID(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
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
}
