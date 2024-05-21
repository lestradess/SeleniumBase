package utiles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Formatter;

public class Logs {
    private static Logger getLogger(){
        String classname = new Exception().getStackTrace()[2].getClassName();
        return LogManager.getLogger(classname);
    }
    public static void trace(String message) {
        Logger log = getLogger();
        log.trace(message);
    }

    public static void debug(String message) {
        Logger log = getLogger();
        log.debug(message);
    }

    public static void info(String message) {
        Logger log = getLogger();
        log.info(message);
    }

    public static void error(String message) {
        Logger log = getLogger();
        log.error(message);
    }

    public static void warn(String message) {
        Logger log = getLogger();
        log.warn(message);
    }

    public static void fatal(String message) {
        Logger log = getLogger();
        log.fatal(message);
    }

    public static void trace(String format, Object... args) {
        Logger log = getLogger();
        log.trace(new Formatter().format(format, args).toString());
    }

    public static void debug(String format, Object... args) {
        Logger log = getLogger();
        log.debug(new Formatter().format(format, args).toString());
    }

    public static void info(String format, Object... args) {
        Logger log = getLogger();
        log.info(new Formatter().format(format, args).toString());
    }

    public static void warn(String format, Object... args) {
        Logger log = getLogger();
        log.warn(new Formatter().format(format, args).toString());
    }

    public static void error(String format, Object... args) {
        Logger log = getLogger();
        log.error(new Formatter().format(format, args).toString());
    }

    public static void fatal(String format, Object... args) {
        Logger log = getLogger();
        log.fatal(new Formatter().format(format, args).toString());
    }
}

