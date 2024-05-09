package pruebas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Pruebas {
    private static final Logger logger = LogManager.getLogger(Pruebas.class);
    public static void main( String[] args ) {

        logger.trace("2.This is a TRACE message.");
        logger.debug("2.This is a DEBUG message.");
        logger.info("3.This is an INFO message.");
        logger.error("5.This is an ERROR message.");
        logger.fatal("2.This is a FATAL message.");

    }
}
