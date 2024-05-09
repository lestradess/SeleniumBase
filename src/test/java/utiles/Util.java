package utiles;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

public class Util {
    public static String readProperty(String key) {
        Properties datos = new Properties();
        try {
            datos.load(Files.newInputStream(new File("config.properties").toPath()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return datos.getProperty(key);
    }
    public static String fechaAMDms(){
        LocalDateTime fecha = LocalDateTime.now();
        int anio = fecha.getYear();
        int mes = fecha.getMonthValue();
        int dia = fecha.getDayOfMonth();
        int hora = fecha.getHour();
        int minuto = fecha.getMinute();
        int segundo = fecha.getSecond();
        return anio+"_"+mes+"_"+dia+"_"+hora+"_"+minuto+"_"+segundo;
    }

}
