package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pagebase.BasePage;


@Test
@CucumberOptions(plugin = { "pretty",
        "json:reportes/cucumber.json",
        "html:reportes/features.html",
        "me.jvt.cucumber.report.PrettyReports:reportes"},   // Configuración de los reportes
        features = {"src/test/resources"},                  // Directorio features
        monochrome = true,                                  //salida de consola más legible
        glue = {"steps"},                          // Directorios de cucumber
        //tags = "@LoginOK",                                //Solo va a ejecutar el test.feature con este tag
        snippets = CucumberOptions.SnippetType.CAMELCASE    //indico que las funciones se creen con camelCase
)
public class BaseCucumberTest extends AbstractTestNGCucumberTests {
    @AfterClass
    public static void cleanDriver() {
        BasePage.tearDown();
    }
}
