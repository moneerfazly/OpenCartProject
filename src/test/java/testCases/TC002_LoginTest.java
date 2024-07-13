package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC002_LoginTest extends BaseTestClass {

    @Test(groups ={"Sanity", "Master"} )
    public void accountLogin() {

        logger.debug("***** Starting TC002_LoginTest *****");
        try {
            //HomePage
            HomePage homePage = new HomePage(driver);
            homePage.clickAccount();
            homePage.clickLogin();

            //Login
            logger.debug("***** Login on user name and password *****");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setUserEmail(p.getProperty("email"));
            loginPage.setUserPassword(p.getProperty("password"));
            loginPage.setClckLogin();

            //MyAccount
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExits();
            Assert.assertEquals(targetPage, true, "Login failed");


        } catch (Exception e) {

            Assert.fail();
        }

        logger.debug("***** Ending TC002_LoginTest *****");


    }

}