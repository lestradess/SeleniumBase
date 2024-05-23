package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagebase.BasePage;
import pages.LoginPage;
import pages.SwagLabsHomePage;
import utiles.Contexto;


public class SwagLabsHomeStep {

    private final LoginPage loginPage = new LoginPage();
    private final SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage();
    private final Contexto contexto = new Contexto();

    public void step(String texto){
        contexto.testSuite = "reportes";
        contexto.test = texto;
    }

    @Given("Navega a la weg de SwagLabs")
    public void navegaALaWegDeSwagLabs() {
        BasePage.navegarUrl();
        //Allure.addAttachment("homepage", BasePage.capturaAllure());
    }

    @When("Introduce credenciales")
    public void introduceCredenciales() {
        step("Introduce credenciales");
        loginPage.login(contexto);
    }

    @And("Presiona el boton Login")
    public void presionaElBotonLogin() {
        step("Presiona el boton Login");
        loginPage.pulsarBtnLogin(contexto);
    }

    @Then("Valida que se entra en la pagina home")
    public void validaQueSeEntraEnLaPaginaHome() {
        step("Valida que se entra en la pagina home");
        swagLabsHomePage.validarPagina(contexto);
    }
    @When("Ordena productos por precio")
    public void ordenaProductosPorPrecio() {
        step("Valida que se entra en la pagina home");
        swagLabsHomePage.ordenarPrecio(contexto);
    }

}
