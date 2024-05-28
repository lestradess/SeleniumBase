package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.HashMap;

public class DriverInit {

    /**
     * LLamar a la función correspondiente según el navegador seleccionado
     *
     * @return WebDriver
     */
    public WebDriver driverSelector() {
        WebDriver driver;

        if (Constants.LIST_NAMES_CHROME.contains(Constants.DRIVER_TYPE)) {
            driver = initChromedriver();
        } else if (Constants.LIST_NAMES_EDGE.contains(Constants.DRIVER_TYPE)) {
            driver = initEdgedriver();
        } else {
            driver = initChromedriver();
        }

        return driver;
    }

    /**
     * Inicializar un contenedor
     * TODO - Implementar inicio testcontainer
     *
     * @return WebDriver
     */
    public WebDriver initDockerDriver() {
        return null;
    }

    /**
     * Configura e inicia el navegador Chrome
     *
     * @return WebDriver
     */
    private WebDriver initChromedriver() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popus", 0);
        chromePrefs.put("download.default_directory", Constants.SYSTEM_PROPERTY_USER_DIR + "\\files");

        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.setExperimentalOption("prefs", chromePrefs);
        if (Constants.WEB_DRIVER_OPTIONS.length != 0) {
            optionsChrome.addArguments(Constants.WEB_DRIVER_OPTIONS);
        }

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(optionsChrome);
    }

    /**
     * Configura e inicia el navegador Edge
     *
     * @return WebDriver
     */
    private WebDriver initEdgedriver() {
        HashMap<String, Object> edgePrefs = new HashMap<>();
        edgePrefs.put("profile.default_content_settings.popups", 0);
        edgePrefs.put("download.default_directory", Constants.SYSTEM_PROPERTY_USER_DIR + "\\files");

        EdgeOptions optionsEdge = new EdgeOptions();
        optionsEdge.setExperimentalOption("prefs", edgePrefs);
        if (Constants.WEB_DRIVER_OPTIONS.length != 0) {
            optionsEdge.addArguments(Constants.WEB_DRIVER_OPTIONS);
        }

        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(optionsEdge);
    }

}
