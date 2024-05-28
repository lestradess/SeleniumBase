package TS01_Login;

import base.BasePage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.LoginPage;

public class Test_TC01_Validar_Login {

    LoginPage loginPage = new LoginPage();

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
        BasePage.testSuite = Test_TC01_Validar_Login.class.getPackage().getName();
        BasePage.testCase = Test_TC01_Validar_Login.class.getSimpleName();
    }

    @Test
    public void test() {
        loginPage.login();
    }

}
