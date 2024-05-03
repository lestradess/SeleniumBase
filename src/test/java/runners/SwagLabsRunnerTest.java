package runners;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagebase.BasePage;
import pages.LoginPage;


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
    }
}
