package testCases;

import Utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC003_LoginDDT extends BaseTestClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
//getting data provider from different class
    public void loginDDT(String email, String pwd, String exp) throws InterruptedException {

        logger.debug("***** Starting TC003_LoginDDT *****");

        try {
            //HomePage
            HomePage homePage = new HomePage(driver);
            homePage.clickAccount();
            homePage.clickLogin();

            //Login
            logger.debug("***** Login on user name and password *****");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setUserEmail(email);
            loginPage.setUserPassword(pwd);
            loginPage.setClckLogin();

            //MyAccount
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExits();

            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }
            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(false);
                }else {
                    Assert.assertTrue(true);
            }
            }
        }catch(Exception e) {
            Assert.fail();
    }
        logger.debug("***** Finished TC003_LoginDDT *****");
        Thread.sleep(3000);

    }
}

