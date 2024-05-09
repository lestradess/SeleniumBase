package utiles;

public class Constantes {
    public static final String WEB_URL = Util.readProperty("WEB_URL");
    public static final String WEB_USER = Util.readProperty("WEB_USER");
    public static final String WEB_PASS = Util.readProperty("WEB_PASS");
    public static final String BROWSER= Util.readProperty("WebDriver.BROWSER");
    public static final String [] DRIVER_OPTIONS=Util.readProperty("WebDriver.DRIVER_OPTIONS").split(", ");
    public static final int ESPERA = Integer.parseInt(Util.readProperty("ESPERA"));

}
