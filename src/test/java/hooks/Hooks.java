package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;


import pagebase.BasePage;
import utiles.Logs;


public class Hooks {

    @Before
    public void before(){
        Logs.trace("Comienza la Ejecución");
    }
    @After
    public void after(){
        Logs.trace("Termina la Ejecución");
        BasePage.cerrarNavegador();
    }
}
