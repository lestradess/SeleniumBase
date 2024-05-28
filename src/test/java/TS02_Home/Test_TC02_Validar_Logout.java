package TS02_Home;

import base.BasePage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Test_TC02_Validar_Logout {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @BeforeClass
    public static void before() {
        setUp();
    }

    @AfterClass
    public static void after() {
        BasePage.closeBrowser();
    }

    public static void setUp() {
        BasePage.initBrowser();
        BasePage.testSuite = Test_TC02_Validar_Logout.class.getPackage().getName();
        BasePage.testCase = Test_TC02_Validar_Logout.class.getSimpleName();
    }

    @Test
    public void test() {
        loginPage.login();
        homePage.logout();
    }

}
