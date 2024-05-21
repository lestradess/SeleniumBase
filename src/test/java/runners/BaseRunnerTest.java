package runners;


import pagebase.BasePage;
import pages.LoginPage;
import pages.SwagLabsHomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class BaseRunnerTest {

    private static final LoginPage loginPage = new LoginPage();
    private static final SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage();

    @BeforeMethod
    public static void setupDriver(){
        BasePage.navegarUrl();
        loginPage.login();
        loginPage.pulsarBtnLogin();
    }
    @AfterMethod
    public static void closeDriver(){
    BasePage.cerrarNavegador();
    }
    @Test
    public static void validarHome() {
       swagLabsHomePage.validarPagina();
    }
    @Test
    public static void ordenarProductos(){
        swagLabsHomePage.ordenarPrecio();
    }
}
