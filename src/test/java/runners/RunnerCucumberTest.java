package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;


@Test
@CucumberOptions(plugin = { "pretty",
        "json:reportes/cucumber.json",
        "html:reportes/features.html",
        "me.jvt.cucumber.report.PrettyReports:reportes"},
        features = {"src/test/resources"},
        monochrome = true,//salida de consola más legible
        glue = {"steps", "hooks"},// Directorios de cucumber
        //tags = "@LoginOK",//Solo va a ejecutar el test.feature con este tag
        snippets = CucumberOptions.SnippetType.CAMELCASE //indico que las funciones se creen con camelCase
)
public class RunnerCucumberTest extends AbstractTestNGCucumberTests {

}
