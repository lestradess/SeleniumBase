package pages;

import base.BasePage;
import org.testng.Assert;

public class HomePage extends BasePage {

    private final String btnUserMenuLocator = "//*[@id='user-menu-button']";
    private final String btnLogoutLocator = "//*[normalize-space(text())='Cerrar Sesión']";

    public HomePage() {
        super(driver);
    }

    public void logout() {
        String textoEsperado = "login";

        clickElement(btnUserMenuLocator);
        clickElement(btnLogoutLocator);

        String rutaActual = driver.getCurrentUrl();

        Assert.assertTrue(rutaActual.contains(textoEsperado), "La página actual no es la página del login");
    }

}

