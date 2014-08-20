package com.testframework.base.Trello;

import com.testframework.base.Utils.WebDriverHelper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElement;
import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElementEnabled;
import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElementToBeVisible;

/**
 * Created by kerua on 7/9/2014.
 */
public class BoardsPage {

    private WebDriver driver;

   @FindBy(how = How.CLASS_NAME, using = "member-initials")
    public static WebElement Member;

    @FindBy(how = How.CLASS_NAME, using = "js-logout")
    public static WebElement LogOut;

    @FindBy(how = How.CLASS_NAME, using = "header-btn-text")
    public static WebElement Boards;

    @FindBy(how = How.LINK_TEXT, using = "Always keep this menu open.")
    public static WebElement Always_keep_this_menu_open;

    @FindBy(how = How.LINK_TEXT, using = "See closed boards…")
    public static WebElement  See_closed_boards;

    @FindBy(how = How.CLASS_NAME, using = "js-open-add-menu")
    public static WebElement AddNnewBoard;

    @FindBy(how = How.CLASS_NAME, using = "js-new-board")
    public static WebElement NewBoard;

    @FindBy(how = How.CLASS_NAME, using = "js-new-org")
    public static WebElement NewOrganization;

    public BoardsPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }

    public void clickLogout() {
        WaitForElementEnabled(Member);
        Member.click();
        WaitForElementEnabled(LogOut);
        LogOut.click();
    }

    public void Logout() {
        clickLogout();
    }

    public void clickBoards() {
        WaitForElementEnabled(Boards);
        Boards.click();
        WaitForElementEnabled(Always_keep_this_menu_open);
        WaitForElementEnabled(See_closed_boards);
    }

    public void addNewBoards() {
        WaitForElementEnabled(AddNnewBoard);
        AddNnewBoard.click();
        WaitForElement(NewBoard);
        WaitForElement(NewOrganization);
    }
}
