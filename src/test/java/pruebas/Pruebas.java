package pruebas;

import utiles.Util;



public class Pruebas {
    public static void main( String[] args ) {
        String navegador = Util.readProperty("WebDriver.BROWSER");
        System.out.println( navegador);
    }
}
