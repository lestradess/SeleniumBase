package org.example.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(plugin = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        features = {"src/test/resources"},
        glue = {"org/example/steps"},
        tags = "@LoginOK"
)
public class RunnerCucumberTest extends AbstractTestNGCucumberTests {

}
