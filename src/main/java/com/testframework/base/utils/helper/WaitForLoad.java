package com.testframework.base.utils.helper;

import org.openqa.selenium.WebElement;

/**
 * Created by kerua on 7/9/2014.
 */
public class WaitForLoad {
    static int timeOut = 5000;
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
}
