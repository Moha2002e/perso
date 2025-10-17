package org.example.server.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration du serveur - Singleton pour charger les paramètres
 * Thread-safe avec chargement statique
 */
public class ServerConfig {
    private static Properties properties;
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = ServerConfig.class.getClassLoader()
                .getResourceAsStream("server.properties")) {
            
            if (input == null) {
                throw new RuntimeException("Impossible de trouver le fichier server.properties");
            }
            
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement du fichier de configuration serveur", e);
        }
    }
    
    // Configuration serveur
    public static int getPortConsultation() {
        return Integer.parseInt(properties.getProperty("PORT_CONSULTATION", "8080"));
    }
    
    // Configuration thread pool
    public static int getThreadPoolSize() {
        return Integer.parseInt(properties.getProperty("THREAD_POOL_SIZE", "10"));
    }
    
    public static int getThreadPoolMaxSize() {
        return Integer.parseInt(properties.getProperty("THREAD_POOL_MAX_SIZE", "20"));
    }
    
    public static int getThreadPoolQueueSize() {
        return Integer.parseInt(properties.getProperty("THREAD_POOL_QUEUE_SIZE", "100"));
    }
    
    public static int getThreadKeepAliveTime() {
        return Integer.parseInt(properties.getProperty("THREAD_KEEP_ALIVE_TIME", "60"));
    }
    
    // Configuration timeouts
    public static int getSocketTimeout() {
        return Integer.parseInt(properties.getProperty("SOCKET_TIMEOUT", "30000"));
    }
    
    public static int getSocketSoTimeout() {
        return Integer.parseInt(properties.getProperty("SOCKET_SO_TIMEOUT", "10000"));
    }
    
    public static int getDbConnectionTimeout() {
        return Integer.parseInt(properties.getProperty("DB_CONNECTION_TIMEOUT", "5000"));
    }
    
    public static int getDbQueryTimeout() {
        return Integer.parseInt(properties.getProperty("DB_QUERY_TIMEOUT", "10000"));
    }
    
    // Configuration logging
    public static String getLogLevel() {
        return properties.getProperty("LOG_LEVEL", "INFO");
    }
    
    public static boolean isLogThreadInfo() {
        return Boolean.parseBoolean(properties.getProperty("LOG_THREAD_INFO", "true"));
    }
    
    public static boolean isLogPerformance() {
        return Boolean.parseBoolean(properties.getProperty("LOG_PERFORMANCE", "true"));
    }
    
    // Configuration arrêt
    public static int getShutdownTimeout() {
        return Integer.parseInt(properties.getProperty("SHUTDOWN_TIMEOUT", "30000"));
    }
    
    public static boolean isGracefulShutdown() {
        return Boolean.parseBoolean(properties.getProperty("GRACEFUL_SHUTDOWN", "true"));
    }
}
