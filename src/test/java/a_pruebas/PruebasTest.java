package a_pruebas;

import org.testng.ITestResult;
import org.testng.annotations.*;
import utiles.Contexto;
import utiles.Logs;
import utiles.Util;

public class PruebasTest {
    private final Contexto contexto = new Contexto();
    private String testname;
    private final String time = Util.fechaAMDms();



    @BeforeClass
    public void beforeClass() {
        contexto.testSuite = Util.fechaAMDms();
        System.out.println(contexto.testSuite);
        Logs.trace("Lanza Beforeclass");
    }
    @AfterClass
    public void afterClass() {

        Logs.trace("Lanza Afterclass");
    }
    @BeforeMethod
    public void setUp(ITestResult resul) {
        testname = resul.getMethod().getMethodName(); // Se usa para consultar el nombre del metodo
        Logs.trace("Lanza BeforeMethod");
    }

    @AfterMethod
    public void tearDown() {
        Logs.trace("Lanza AfterMethod");
    }

    @Test(priority = 1, testName = "Test1")
    public void oneTest() {
        Logs.info(testname);
        Logs.debug(time);
        Logs.trace("Lanza oneTest");
    }

    @Test(priority = 2, testName = "Test2")
    public void dosTest() {
        Logs.info(testname);
        Logs.debug(time);
        Logs.trace("lanza dosTest");
    }

}
