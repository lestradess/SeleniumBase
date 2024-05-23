package pages;

import pagebase.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utiles.Contexto;
import utiles.Logs;

public class SwagLabsHomePage extends BasePage {


    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement selectProducts;

    public SwagLabsHomePage() {super(driver);}
    public void validarPagina(Contexto contexto){
        espera(2);
        Logs.info(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Swag Labs");
        tomarCaptura(contexto);
    }

    public void ordenarPrecio(Contexto contexto){
        espera(2);
        Logs.info("Selecciona indice 2");
        seleccionar(selectProducts,1);
        tomarCaptura(contexto);
        espera(2);
        Logs.info("selecciona Price (low to high)");
        seleccionar(selectProducts,"Price (low to high)");
        tomarCaptura(contexto);
        espera(5);
        Logs.info("ordena precio ok");
    }
}
