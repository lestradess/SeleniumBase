package utiles;

import java.io.File;
import java.nio.file.Files;
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
}
