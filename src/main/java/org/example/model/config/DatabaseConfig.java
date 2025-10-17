package org.example.model.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static Properties properties;
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = DatabaseConfig.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            
            if (input == null) {
                throw new RuntimeException("Impossible de trouver le fichier database.properties");
            }
            
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement du fichier de configuration", e);
        }
    }
    
    public static String getHost() {
        return properties.getProperty("db.host");
    }
    
    public static String getDatabaseName() {
        return properties.getProperty("db.name");
    }
    
    public static String getUsername() {
        return properties.getProperty("db.user");
    }
    
    public static String getPassword() {
        return properties.getProperty("db.password");
    }
    
    public static String getDriver() {
        return properties.getProperty("db.driver");
    }
    
    public static String getUrl() {
        // Construire l'URL dynamiquement pour éviter les problèmes de variables non résolues
        String host = getHost();
        String database = getDatabaseName();
        return "jdbc:mysql://" + host + "/" + database;
    }
}
