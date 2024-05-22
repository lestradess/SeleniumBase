package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagebase.BasePage;
import pages.LoginPage;
import pages.SwagLabsHomePage;

public class SwagLabsHomeStep {

    private final LoginPage loginPage = new LoginPage();
    private final SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage();


    @Given("Navega a la weg de SwagLabs")
    public void navegaALaWegDeSwagLabs() {
        BasePage.navegarUrl();
        //Allure.addAttachment("homepage", BasePage.capturaAllure());
    }

    @When("Introduce credenciales")
    public void introduceCredenciales() {
        loginPage.login();
    }

    @And("Presiona el boton Login")
    public void presionaElBotonLogin() {
        loginPage.pulsarBtnLogin();
    }

    @Then("Valida que se entra en la pagina home")
    public void validaQueSeEntraEnLaPaginaHome() {
        swagLabsHomePage.validarPagina();
    }
    @When("Ordena productos por precio")
    public void ordenaProductosPorPrecio() {
        swagLabsHomePage.ordenarPrecio();
    }

}
