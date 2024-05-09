package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pagebase.BasePage;


public class Hooks {
    protected static Logger logger = LogManager.getLogger(BasePage.class);
    @Before
    public void before(){
        logger.trace("Comienza la Ejecución");
    }
    @After
    public void after(){
        logger.trace("Termina la Ejecución");
        BasePage.cerrarNavegador();

    }
}
