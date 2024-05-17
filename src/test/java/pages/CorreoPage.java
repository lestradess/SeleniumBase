package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pagebase.BasePage;

public class CorreoPage extends BasePage {

    @FindBy(xpath = "//input[@id='txtBuscadorMenu']")
    WebElement inputCorreo;
    @FindBy(xpath = "//button[@id='btnMenuBusqueda']")
    WebElement btnBuscar;
    @FindBy(xpath = "//*[text()='Su petición ha terminado con éxito.']")WebElement exito;

    public CorreoPage() {
        super(driver);
    }


    public void mandarCorreos(Integer numero) {
        for (int i = 0; i <numero; i++) {
            informar(inputCorreo, "aaa@bbb.com");
            click(btnBuscar);
            Assert.assertNotNull(exito);

        }

    }

}
