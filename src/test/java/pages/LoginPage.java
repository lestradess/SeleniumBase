package pages;

import base.BasePage;
import org.testng.Assert;
import utils.Constants;

public class LoginPage extends BasePage {

    private final String usernameLocator = "//*[@formcontrolname='user']";
    private final String passwordLocator = "//*[@formcontrolname='pass']";
    private final String btnLoginLocator = "//*[contains(@class,'login-button')]";

    public LoginPage() {
        super(driver);
    }

    public void login() {
        String textoEsperado = "Kiosko VACS";

        navigateTo();
        login(usernameLocator, passwordLocator, btnLoginLocator, Constants.WEB_USER_ADMINISTRADOR, Constants.WEB_PASS_ADMINISTRADOR);

        String textoActual = read("//app-header//*[contains(@class,'header-title')]");
        Assert.assertEquals(textoActual, textoEsperado);
    }

}

