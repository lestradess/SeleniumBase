package pagebase;

import org.apache.commons.io.FileUtils;

import utiles.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage extends Logs {

    protected static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constantes.ESPERA));

    static {
        driver = WebdriverConfig.createBrowser();
    }

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void navegarUrl() {
        trace("Abriendo navegador");

        if (driver != null) {
            driver.get(Constantes.WEB_URL);
            trace("Navegador abierto");
        } else {
            error("no hay instancia del driver");
        }

    }

    public static void navegarUrl(String url) {
        driver.get(url);
    }

    public static void cerrarNavegador() {
        driver.close();
        trace("Navegador cerrado");
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            trace("Instancia del driver cerrada");
        }
    }

    public void espera(int segundos) { //!Solo utilizar para pruebas
        int tiempo = segundos * 1000;
        try {
            trace("Espera de " + segundos + " segundos");
            Thread.sleep(tiempo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void marca(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
    }

    private WebElement esperaElemento(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element) {
        esperaElemento(element).click();

    }

    public void informar(WebElement element, String texto) {
        esperaElemento(element).sendKeys(texto);
    }

    public static void tomarCaptura(Contexto contexto) {
        String fecha;
        String testSuite;
        String test;
        String directorio;
        String filePath;

        try {
            if (contexto.testSuite.equalsIgnoreCase("reportes")) {
                directorio = contexto.testSuite;
                fecha = "cucumber-html-reports";
                testSuite = "images";
                test = contexto.test;
                filePath = System.getProperty("user.dir") + "/" + directorio + "/" + fecha + "/" + testSuite + "/"+Util.fechaAMDms() + test + ".png";
            } else {
                fecha = contexto.fecha;
                testSuite = contexto.testSuite;
                test = contexto.test;
                directorio = "logs";
                filePath = System.getProperty("user.dir") + "/" + directorio + "/" + fecha + "/" + testSuite + "/" + test + "_" + Util.fechaAMDms() + ".png";
            }

            TakesScreenshot captura = ((TakesScreenshot) driver);

            File file = captura.getScreenshotAs(OutputType.FILE);

            File desFile = new File(filePath);

            FileUtils.copyFile(file, desFile);
        } catch (IOException e) {
            error("BasePage/tomarCaptura: Error al capturar pantalla");
            throw new RuntimeException(e);

        }

    }

    //    public static InputStream capturaAllure() {
//        return new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
//    }
    public void seleccionar(WebElement element, int index) {
        Select select = new Select(esperaElemento(element));
        select.selectByIndex(index);
    }

    public void seleccionar(WebElement element, String texto) {
        Select select = new Select(esperaElemento(element));
        //select.selectByValue(texto);
        select.selectByVisibleText(texto);
    }
}
