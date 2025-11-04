package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar config.properties", e);
        }
    }

    public static String getAmbiente() {
        return properties.getProperty("ambiente");
    }

    public static String getBaseUrl() {
        String ambiente = getAmbiente();
        return properties.getProperty("url." + ambiente);
    }
}
