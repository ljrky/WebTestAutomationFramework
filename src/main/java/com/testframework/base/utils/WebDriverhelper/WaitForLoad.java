package com.testframework.base.Utils.WebDriverhelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by kerua on 7/9/2014.
 */
public class WaitForLoad {
    static int timeOut = 5000;
    static int timeOutSeconds = 5;
    static int numberOfWait = 5;

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


    public static void WaitForPageToLoad(WebElement FooterOfThePage){
        for (int i = 0; i < numberOfWait; i++){
            try {
                if (FooterOfThePage.isDisplayed()){
                    break;
                }
                Thread.sleep(timeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void WaitForSuccess(WebDriver driver){
        new WebDriverWait(driver, 25).until(
                ExpectedConditions.visibilityOfElementLocated(By.className("successful"))
        );
    }

    public static void WaitForPageToLoad(WebDriver driver){
        driver.manage().timeouts().pageLoadTimeout(timeOutSeconds,TimeUnit.SECONDS);
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
}
