package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pagebase.BasePage;
import utiles.Constantes;
import utiles.Contexto;
import utiles.Logs;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@id='user-name']")
    WebElement user;
    @FindBy(xpath = "//*[@id='password']")
    WebElement pass;
    @FindBy(xpath = "//*[@type='submit']")
    WebElement btnLogin;


    public LoginPage() {
        super(driver);
    }

    public void login(Contexto contexto) {
        marca(user);
        //espera(5);
        informar(user, Constantes.WEB_USER);
        tomarCaptura(contexto);
        Logs.info("Se ha introducido el usuario");
        //espera(5);
        marca(pass);
        informar(pass, Constantes.WEB_PASS);
        Logs.info("Se ha introducido la contraseña");
        tomarCaptura(contexto);
        //espera(5);
    }
    public void pulsarBtnLogin(Contexto contexto){
        tomarCaptura(contexto);
        click(btnLogin);
        Logs.info("Se ha pulsado el botón Login");
        tomarCaptura(contexto);
        //espera(5);
    }


}
