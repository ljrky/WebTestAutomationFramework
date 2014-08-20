package com.testframework.test.SampleSite;

import com.testframework.base.Trello.BoardsPage;
import com.testframework.base.Trello.LoginPage;
import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.Trello.LogoutPage;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;
import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElement;

public class BoardTest extends SimpleTestCase {

    public String email, password;

    @BeforeClass
    public void initVariables(){
        ResourceBundle resourceBundle = getResourceBundle("com.testframework.test.SampleSite.HomePageTest");
        email = resourceBundle.getString("email");
        password = resourceBundle.getString("password");
    }

    @BeforeMethod
    public void beforeMethod(){
        //Login Function
        Reporter.log("Navigate to login page; \n", 1);
        LoginPage signInPage = new LoginPage(driver);
        Reporter.log("Enter valid login and password; \n", 1);
        signInPage.Login(email, password);
    }

    @AfterMethod
    public void afterMethod(){
        //Logout Function
        Reporter.log("Navigate to logout page; \n", 1);
        BoardsPage boardsPage = new BoardsPage(driver);
        boardsPage.Logout();
        Reporter.log("Logout page is loaded; \n", 1);
        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.LogoutLoaded();
    }

    @Test()
    public void LoadBoardContentTest() {
        Reporter.log("Create Board Test\n", 1);
        Reporter.log("1) Navigate to Board\n", 1);
        Reporter.log("2) Click Boards button, and verify content in the boards\n", 1);
        BoardsPage boardsPage = new BoardsPage(driver);
        boardsPage.clickBoards();
    }

    @Test()
    public void AddNewBoardOrOrganizationTest() {
        Reporter.log("Add New Board Or Organization Test\n", 1);
        Reporter.log("1) Navigate to Board\n", 1);
        Reporter.log("2) Click Add New Board Or Organization button, and verify content\n", 1);
        BoardsPage boardsPage = new BoardsPage(driver);
        boardsPage.addNewBoards();
    }
}
