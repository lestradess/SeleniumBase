package org.example.runners;


import org.example.pagebase.BasePage;
import org.example.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SwagLabsRunnerTest {

    @BeforeMethod
    public static void setupDriver(){
        BasePage.navegarUrl();

    }
    @AfterMethod
    public static void closeDriver(){
    BasePage.cerrarNavegador();
    }
    @Test
    public static void login() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        loginPage.pulsarLogin();
    }
}
