package com.testframework.base.Utils.WebDriverhelper;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by kerua on 7/9/2014.
 */
public class WaitForLoad {
    static int timeOut = 5000;
    static int timeOutSeconds = 5;
    static int numberOfWait = 5;
    static int waitForTransactionSuccess = 60;
    public static int waitForElementTimeout = 30;


    public static void SetImplicitlyWaitForAllElement(WebDriver driver) {
        System.out.println("waitForElementTimeout is set to : " + waitForElementTimeout);
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void WaitForSeconds(int TimeOutSeconds, WebDriver driver){
        driver.manage().timeouts().implicitlyWait(TimeOutSeconds,TimeUnit.SECONDS);
    }

    public static WebElement WaitForElementToBeClick(WebDriver driver, By by){
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement WaitForElementToBeVisible(WebDriver driver, WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void WaitForFrameAndSwitchTo(WebDriver driver, String frameLocator){
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    public static void WaitWithOutExcpetion(WebDriver driver, By by){
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(timeOutSeconds,TimeUnit.SECONDS).pollingEvery(10,TimeUnit.MILLISECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static boolean IsElementExistByLinkText(WebDriver driver, String Locator){
        try{
            driver.findElement(By.linkText(Locator));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
