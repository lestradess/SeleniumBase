package utils;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Utils {

    public static Properties prop;

    static {
        try {
            prop = new Properties();
            prop.load(Files.newInputStream(new File(Constants.CONFIG_PROPERTIES).toPath()));

            prepareReplacements();
        } catch (Exception e) {
            try {
                throw new Exception("No se encuentra el fichero config.properties");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Modificar las variable en el config.properties por los valores introducidos por el sistema en la ejecución
     */
    private static void prepareReplacements() {
        Map<String, String> replacements = new HashMap<>();
        replacements.put("WEB_URL", "functional.test.url");
        replacements.put("WEB_ADFS", "adfs.url");
        replacements.put("WEB_DOMINIO", "web.domain");
        replacements.put("WEB_USER_ADMINISTRADOR", "G503630-username");
        replacements.put("WEB_PASS_ADMINISTRADOR", "G503630-credentials");

        for (Map.Entry<String, String> replacement : replacements.entrySet()) {
            // Si tenemos una propiedad del sistema para cada remplazo,
            // cambiamos la propiedad por la varible
            String property = System.getProperty(replacement.getValue());
            if (null != property) {
                prop.setProperty(replacement.getKey(), property);
            }
        }
    }

    /**
     * Obtener fecha con formato DMAhms
     *
     * @return fecha con formato dia_mes_anno_hora_minutos_segundos_milisegundos
     */
    public static String fechaDMAhms() {
        LocalDateTime dateTime = LocalDateTime.now();

        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int minutes = dateTime.getMinute();
        int seconds = dateTime.getSecond();
        int mili = dateTime.getNano();

        return day + Constants.DATE_TIME_SEPARATOR + month + Constants.DATE_TIME_SEPARATOR + year + Constants.DATE_TIME_SEPARATOR
                + hour + Constants.DATE_TIME_SEPARATOR + minutes + Constants.DATE_TIME_SEPARATOR + seconds + Constants.DATE_TIME_SEPARATOR + mili;
    }

}

