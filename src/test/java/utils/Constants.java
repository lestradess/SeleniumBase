package utils;

import java.io.File;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String CONFIG_PROPERTIES = "config.properties";
    public static final String WEB_DRIVER_BROWSER = "WebDriver.BROWSER";
    public static final String[] WEB_DRIVER_OPTIONS = Utils.prop.getProperty("WebDriver.DRIVER_OPTIONS").split(", ");
    public static final String DRIVER_TYPE = Utils.prop.getProperty(WEB_DRIVER_BROWSER).toLowerCase().replace(" ", "");
    public static final List<String> LIST_NAMES_CHROME = Arrays.asList("chrome", "googlechrome", "remotechrome");
    public static final List<String> LIST_NAMES_EDGE = Arrays.asList("edge", "msedge", "remoteedge");
    public static final String WEB_URL = Utils.prop.getProperty("WEB_URL");
    public static final String WEB_ADFS = Utils.prop.getProperty("WEB_ADFS");
    public static final String WEB_DOMINIO = Utils.prop.getProperty("WEB_DOMINIO");
    public static final String WEB_USER_ADMINISTRADOR = Utils.prop.getProperty("WEB_USER_ADMINISTRADOR");
    public static final String WEB_PASS_ADMINISTRADOR = Utils.prop.getProperty("WEB_PASS_ADMINISTRADOR");
    public static final String XPATH_SPINER = Utils.prop.getProperty("XPATH_SPINER");
    public static final String TIME_MIN_SPINER = Utils.prop.getProperty("TIME_MIN_SPINER");
    public static final String TIME_MAX_SPINER = Utils.prop.getProperty("TIME_MAX_SPINER");
    public static final String EXPLICIT_TIME = Utils.prop.getProperty("EXPLICIT_TIME");
    public static final String SYSTEM_PROPERTY_USER_DIR = System.getProperty("user.dir");
    public static final String DATE_TIME_SEPARATOR = "_";
    public static final String ALWAYS = "Always";
    public static final String ONLY_ON_ERROR = "Only on error";
    public static final String NEVER = "Never";
    public static final String SCREENSHOT_FREQUENCY = Utils.prop.getProperty("SCREENSHOT_FREQUENCY");
    public static final String FILE_SYSTEM_SEPARATOR = FileSystems.getDefault().getSeparator();
    public static final String PATH_LOGS = SYSTEM_PROPERTY_USER_DIR + FILE_SYSTEM_SEPARATOR + "logs";
    public static final String DATE_FORMAT = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm"));
    public static final File FOLDER_LOGS = new File(PATH_LOGS + FILE_SYSTEM_SEPARATOR + DATE_FORMAT);
    public static final String SCREENSHOTS_EXTENSION = ".png";

}
