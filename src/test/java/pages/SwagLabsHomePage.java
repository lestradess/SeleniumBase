package pages;

import pagebase.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SwagLabsHomePage extends BasePage {

    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement selectProducts;

    public SwagLabsHomePage() {super();}
    public void validarPagina(){
        espera(2);
        logger.info(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Swag Labs");
    }

    public void ordenarPrecio(){
        espera(2);
        logger.info("Selecciona indice 2");
        seleccionar(selectProducts,1);
        espera(2);
        logger.info("selecciona Price (low to high)");
        seleccionar(selectProducts,"Price (low to high)");
        espera(5);
        logger.info("ordena precio ok");
    }
}