package org.example.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.pagebase.BasePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class SwagLabsTestStep {

    private LoginPage loginPage;
    @Step
    @Given("Navega a la weg de SwagLabs")
    public void navega_a_la_weg_de_swag_labs() {

        BasePage.navegarUrl();

        Allure.addAttachment("homepage", BasePage.capturaAllure());
    }

    @When("Introduce credenciales")
    public void introduce_credenciales() {
        this.loginPage = new LoginPage();
        loginPage.login();
    }

    @And("Presiona el boton Login")
    public void presiona_el_boton_login() {
        this.loginPage = new LoginPage();
        loginPage.pulsarLogin();
    }

    @Then("Valida que se entra en la página home")
    public void valida_que_se_entra_en_la_página_home() {
    this.loginPage = new LoginPage();
        loginPage.validarPagina();
    }

}
