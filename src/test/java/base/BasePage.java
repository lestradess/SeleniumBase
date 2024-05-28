package base;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import utils.Constants;
import utils.DriverInit;
import utils.Utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    public static String testSuite;
    public static String testCase;
    /**
     * Variable estática 'driver' de tipo Webdriver
     * Esta variable va a ser compartidda por todas las instanicas de BasePage y sus subclases
     */
    protected static WebDriver driver;
    protected static String handler;
    protected static JavascriptExecutor js;
    /**
     * Variable de instancia 'wait' de tipo WebdriverWait
     * Se inicializa inmediatamente con una instancia de WebdriverWait utilizando el 'driver' estático
     * WebdriverWait se usa para poner esparas explícitas en los elementos web
     */
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(Constants.EXPLICIT_TIME)));

    /**
     * Constructor de BasePage
     *
     * @param driver objeto Webdriver
     */
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    /**
     * Iniciar instancia del driver
     *
     */
    public static void initBrowser() {
        DriverInit driverInit = new DriverInit();

        if (Constants.DRIVER_TYPE.contains("container")) {
            driver = driverInit.initDockerDriver();
        } else {
            driver = driverInit.driverSelector();
        }

        handler = driver.getWindowHandle();
        js = (JavascriptExecutor) driver;
    }

    /**
     * Cerrar la instancia del driver
     */
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Realizar y guardar capturas de pantalla del navegador
     */
    public void screenshot() {
        if (!Constants.SCREENSHOT_FREQUENCY.equals(Constants.NEVER)) {
            try {
                String name = Utils.fechaDMAhms() + Constants.SCREENSHOTS_EXTENSION;
                String filePath = Constants.FOLDER_LOGS + Constants.FILE_SYSTEM_SEPARATOR + testSuite + Constants.FILE_SYSTEM_SEPARATOR + testCase + Constants.FILE_SYSTEM_SEPARATOR + "/screenshots/" + Constants.FILE_SYSTEM_SEPARATOR + name;

                TakesScreenshot screenshot = (TakesScreenshot) driver;

                File file = screenshot.getScreenshotAs(OutputType.FILE);
                File desFile = new File(filePath);

                FileUtils.copyFile(file, desFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Navegar a la URL establecida en el config.properties
     */
    public void navigateTo() {
        driver.get(Constants.WEB_URL);
        waitSpiner();
        screenshot();
    }

    /**
     * Navegar a la URL pasada como parámetro
     *
     * @param url página web a la cual se desea navegar
     */
    public void navigateTo(String url) {
        driver.get(url);
        waitSpiner();
        screenshot();
    }

    /**
     * Realizar login con SAML
     */
    public void login(String username, String password) {
        String dominio = Constants.WEB_DOMINIO;
        String adfs = Constants.WEB_ADFS;

        String loginSAML = "https://xxxuser:xxxpassword@dominio";
        loginSAML = loginSAML.replace("xxxuser", dominio + username);
        loginSAML = loginSAML.replace("xxxpassword", password);
        loginSAML = loginSAML.replace("dominio", adfs);

        driver.get(loginSAML);

        pause();
        screenshot();
    }

    /**
     * Realizar login normal
     */
    public void login(String usernameLocator, String passwordLocator, String btnLoginLocator, String username, String password) {
        write(usernameLocator, username);
        write(passwordLocator, password);
        clickElement(btnLoginLocator);
        pause();
        screenshot();
    }

    /**
     * Encontrar y devolver un WebElement en la página utilizando un locator XPath,
     * esperando a qué sea clickable en el DOM
     *
     * @param locator locator del elemento web
     */
    private WebElement find(String locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    /**
     * Esperar hasta que se quite los spiners en caso de que aparezcan en la web
     */
    public void waitSpiner() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(Constants.TIME_MIN_SPINER)));
        List<WebElement> spiners = driver.findElements(By.xpath(Constants.XPATH_SPINER));
        new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(Constants.TIME_MAX_SPINER))).until(ExpectedConditions.invisibilityOfAllElements(spiners));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(Constants.TIME_MAX_SPINER)));
    }

    /**
     * Esperar el tiempo indicado en la constante EXPLICIT_TIME antes de continuar con la ejecución
     */
    public void pause() {
        try {
            Thread.sleep(Long.parseLong(Constants.EXPLICIT_TIME) * 1000);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    /**
     * Esperar el tiempo indicado por parámetro (en milisegundos) antes de continuar con la ejecución
     *
     * @param time tiempo de espera en milisegundos
     */
    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    /**
     * Realizar la acción click sobre un elemento
     *
     * @param locator locator del elemento web
     */
    public void clickElement(String locator) {
        find(locator).click();
        waitSpiner();
        screenshot();
    }

    /**
     * Realizar la acción click derecho sobre un elemento
     *
     * @param locator locator del elemento web
     */
    public void rightClickElement(String locator) {
        Actions actions = new Actions(driver);
        actions.contextClick(find(locator)).perform();
        screenshot();
    }

    /**
     * Realizar la acción doble click sobre un elemento
     *
     * @param locator locator del elemento web
     */
    public void doubleClickElement(String locator) {
        Actions actions = new Actions(driver);
        actions.doubleClick(find(locator)).perform();
        screenshot();
    }

    /**
     * Realizar la acción de escribir un texto en un elemento
     *
     * @param locator    locator del elemento web
     * @param keysToSend texto que se escribe
     */
    public void write(String locator, String keysToSend) {
        find(locator).clear();
        find(locator).sendKeys(keysToSend);
        screenshot();
    }

    /**
     * Obtener el texto de un elemento web
     *
     * @param locator locator del elemento web
     * @return texto de un elemento web
     */
    public String read(String locator) {
        return find(locator).getText();
    }

    /**
     * Obtener el valor de un atributo de un elemento web
     *
     * @param locator locator del elemento web
     * @return valor del atributo
     */
    public String read(String locator, String atribute) {
        return find(locator).getAttribute(atribute);
    }

    /**
     * Realizar la acción de seleccionar una opción de un select a partir de su valor
     *
     * @param locator locator del elemento web
     * @param value   valor que se desea seleccionar
     */
    public void selectFromDropdownByValue(String locator, String value) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByValue(value);
        screenshot();
    }

    /**
     * Realizar la acción de seleccionar una opción de un select a partir de su posición
     *
     * @param locator locator del elemento web
     * @param index   posición de la opción que se desea seleccionar
     */
    public void selectFromDropdownByIndex(String locator, int index) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByIndex(index);
        screenshot();
    }

    /**
     * Devolver el número de opciones que contiene un select
     *
     * @param locator locator del elemento web
     */
    public int dropdownSize(String locator) {
        Select dropdown = new Select(find(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();

        return dropdownOptions.size();
    }

    /**
     * Devolver todos los valores que contiene un select
     *
     * @param locator locator del elemento web
     */
    public List<String> getDropdownValues(String locator) {
        Select dropdown = new Select(find(locator));

        List<WebElement> dropdownOptions = dropdown.getOptions();
        List<String> values = new ArrayList<>();

        for (WebElement option : dropdownOptions) {
            values.add(option.getText());
        }

        return values;
    }

    /**
     * Navegar a la ventana inicial
     */
    public void switchToDefault() {
        driver.switchTo().window(handler);
        screenshot();
    }

    /**
     * Navegar al cotexto de un iframe
     *
     * @param contextID contexto del ifrmame
     */
    public void switchToFrame(String contextID) {
        driver.switchTo().frame(contextID);
        screenshot();
    }

    /**
     * Cambiar de ventana del navegador
     *
     * @param contextID contexto de la pestaña del navegador
     */
    public void switchToWindow(String contextID) throws Exception {
        boolean ContextChangeWINDOW = false;
        String originalContextChangeWINDOW = driver.getWindowHandle();
        List<String> ContextChangeWINDOWHandlesList = new ArrayList<>(driver.getWindowHandles());

        for (String winHandle : ContextChangeWINDOWHandlesList) {
            if (!originalContextChangeWINDOW.contentEquals(winHandle)) {
                if (driver.switchTo().window(winHandle).getTitle().equals(contextID)) {
                    ContextChangeWINDOW = true;
                    break;
                }
            }
        }

        if (!ContextChangeWINDOW && contextID.matches("-?\\d+")) {
            if (Integer.parseInt(contextID) <= ContextChangeWINDOWHandlesList.size()) {
                driver.switchTo().window(ContextChangeWINDOWHandlesList.get(Integer.parseInt(contextID)));
                ContextChangeWINDOW = true;
            }
        }

        if (!ContextChangeWINDOW) throw new Exception("Error: Window/Tab " + contextID + " not found");

        screenshot();
    }

    /**
     * Ejecutar js script. Ejemplo: document.querySelector('#maggioranza').click();
     *
     * @param script comando a ejecutar
     */
    public void executeScript(String script) {
        js.executeScript(script);
        screenshot();
    }

    /**
     * Ejecutar js script
     *
     * @param script comando a ejecutar
     */
    public void executeScript(String script, int position) {
        js.executeScript(script, position);
        screenshot();
    }

    /**
     * Ejecutar js script para devolver un elemento web
     *
     * @param script  comando a ejecutar
     * @param element elemento web
     * @return web element
     */
    public WebElement executeScript(String script, WebElement element) {
        return (WebElement) js.executeScript(script, element);
    }

    /**
     * Subir un archivo a un elemento web input de tipo file
     *
     * @param locator  locator del elemento web
     * @param filePath ruta del archivo
     */
    public void uploadFileInput(String locator, String filePath) {
        if (!new File(filePath).isAbsolute()) {
            if (!filePath.startsWith(File.pathSeparator)) filePath = File.separator + filePath;
            filePath = Constants.SYSTEM_PROPERTY_USER_DIR + filePath;
        }
        LocalFileDetector detector = new LocalFileDetector();
        File localFile = detector.getLocalFile(filePath);
        WebElement element = find(locator);
        ((RemoteWebElement) element).setFileDetector(detector);
        element.sendKeys(localFile.getAbsolutePath());

        screenshot();
    }

    /**
     * Subir un archivo a un elemento web que NO es input de tipo file
     *
     * @param locator  locator del elemento web
     * @param filePath ruta del archivo
     */
    public void uploadFile(String locator, String filePath) {
        // Hacer click en el botón para abrir el diálogo de carga de archivos
        clickElement(locator);

        // Utilizar la clase Robot para interactuar con el cuadro de diálogo del sistema operativo y seleccionar el archivo
        try {
            Robot robot = new Robot();
            StringSelection seleccion = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(seleccion, null);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            screenshot();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    /**
     * Descargar un archivo y validar que la carpeta donde se va a descargar no esté vacío
     * TODO - Modificar para que descargue el archivo en la carpeta del test que lo ejecuta
     *
     * @param locator locator del elemento web
     */
    public void downloadFile(String locator) {
        clickElement(locator);

        File folder = new File(Constants.SYSTEM_PROPERTY_USER_DIR + "\\files");
        File[] listFiles = folder.listFiles();

        assert listFiles != null;
        Assert.assertTrue("Fichero no descargado correctamente", listFiles.length > 0);
    }

    /**
     * Realizar scroll hacia abajo
     */
    public void scrollDown() {
        executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Realizar scroll hacia arriba
     */
    public void scrollUp() {
        executeScript("window.scrollTo(0, 0)");
    }

    /**
     * Realizar scroll hasta un elemento
     *
     * @param locator locator del elemento web
     */
    public void scrollToElement(String locator) {
        // Se obtiene la altura de la ventana del navegador
        long windowHeight = (Long) js.executeScript("return window.innerHeight || document.documentElement.clientHeight");

        // Se obtiene la posición vertical del elemento
        int elementPosition = find(locator).getLocation().getY();

        // Se calcula la posición de desplazamiento para centrar el elemento
        int scrollPosition = elementPosition - (int) (windowHeight / 2);

        // Se realiza el scroll hasta la posición centrada
        executeScript("window.scrollTo(0, arguments[0]);", scrollPosition);
    }

    /**
     * Localizar y obtener un nodo ShadowRoot para trabajar con ShadowDOM
     *
     * @param locator locator del nodo ShadowHost
     * @return ShadowRoot
     */
    public WebElement shadowRoot(String locator) {
        WebElement nodoShadowHost = find(locator);
        return executeScript("return arguments[0].shadowRoot", nodoShadowHost);
    }

}
