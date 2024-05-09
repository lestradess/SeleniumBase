package pagebase;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utiles.WebdriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utiles.Constantes;
import utiles.Util;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    protected static WebDriver driver = WebdriverConfig.createBrowser();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constantes.ESPERA));
    protected static Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage() {
        //BasePage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void navegarUrl() {
        driver.get(Constantes.WEB_URL);
    }

    public static void navegarUrl(String url) {
        driver.get(url);
    }

    public static void cerrarNavegador() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
        logger.info("Navegador cerrado");
    }

    public void espera(int segundos) { //!Solo utilizar para pruebas
        int tiempo = segundos * 1000;
        try {
            logger.trace("Espera de " + segundos + " segundos");
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

    public static void tomarCaptura() {

        try {

            String filePath = System.getProperty("user.dir") + "/logs/capturas/" + Util.fechaAMDms() + ".png";

            TakesScreenshot captura = ((TakesScreenshot) driver);

            File file = captura.getScreenshotAs(OutputType.FILE);

            File desFile = new File(filePath);

            FileUtils.copyFile(file, desFile);
        } catch (IOException e) {
            logger.error("BasePage/tomarCaptura: Error al capturar pantalla");
            throw new RuntimeException(e);

        }

    }

//    public static InputStream capturaAllure() {
//        return new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
//    }
public void seleccionar(WebElement element, int index){
    Select select = new Select(esperaElemento(element));
    select.selectByIndex(index);
}
    public void seleccionar(WebElement element, String texto){
        Select select = new Select(esperaElemento(element));
        //select.selectByValue(texto);
        select.selectByVisibleText(texto);
    }
}
