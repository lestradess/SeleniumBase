package org.example.utiles;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebdriverConfig {
    static WebDriver driver;

    static String navegador = Constantes.BROWSER;

    public static WebDriver createBrowser() {
        switch (navegador) {
            case "chrome":
                //WebDriverManager.chromedriver().clearDriverCache();
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(Constantes.DRIVER_OPTIONS);

                driver = new ChromeDriver(chromeOptions);

                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalStateException("NAVEGADOR NO RECONOCIDO: " + navegador);
        }
        return driver;
    }



}
