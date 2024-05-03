package pagebase;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utiles.Constantes;
import utiles.WebdriverConfig;

import java.time.Duration;

public class BasePage {
    protected static WebDriver driver = WebdriverConfig.createBrowser();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constantes.ESPERA));

    public BasePage(WebDriver driver){
        BasePage.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public static void navegarUrl(){
        driver.get(Constantes.WEB_URL);
    }

    public static void navegarUrl(String url){
        driver.get(url);
    }
    public static void cerrarNavegador(){
        if (driver !=null){
            driver.quit();
        }
        System.out.println("Navegar cerrado");
    }

    public void espera(int segundos) { //!Solo utilizar para pruebas
        int tiempo = segundos *1000;
        try {
            System.out.println("Espera de "+ segundos + " segundos");
            Thread.sleep(tiempo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void marca(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
    }
    private WebElement esperaElemento(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
        public void click(WebElement element){
        esperaElemento(element).click();

    }
    public void informar(WebElement element, String texto){
        esperaElemento(element).sendKeys(texto);
    }
}
