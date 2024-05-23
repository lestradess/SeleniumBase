package runners;


import org.testng.ITestResult;
import org.testng.annotations.*;
import pagebase.BasePage;
import pages.LoginPage;
import pages.SwagLabsHomePage;
import utiles.Contexto;
import utiles.Util;


public class BaseRunnerTest {

    private static final LoginPage loginPage = new LoginPage();
    private static final SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage();
    private static String testname;
    private static final Contexto contexto = new Contexto();

    @BeforeClass
    public void afterClass() {
        contexto.fecha = Util.fechaAMDms();
        contexto.testSuite = "TestSuit01"; //Hay que poner como queremos llamar al testSuit
    }

    @BeforeMethod
    public static void setupDriver(ITestResult resul) {
        testname = resul.getMethod().getMethodName(); // Se usa para consultar el nombre del metodo
        contexto.test = "Before";
        BasePage.navegarUrl();
        loginPage.login(contexto);
        loginPage.pulsarBtnLogin(contexto);
    }

    @AfterMethod
    public static void closeDriver() {
        //BasePage.cerrarNavegador();
    }

    @Test(priority = 1, testName = "Validar Home")
    public static void validarHome() {
        contexto.test = testname;
        swagLabsHomePage.validarPagina(contexto);
    }

    @Test(priority = 2, testName = "Ordenar productos")
    public static void ordenarProductos() {
        contexto.test = testname;
        swagLabsHomePage.ordenarPrecio(contexto);
    }

    @AfterClass
    public static void cleanDriver() {
        BasePage.tearDown();
    }
}
