package org.example.testBd;

import org.example.model.BD.ConnectDB;
import org.example.model.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnection {
    
    public static void main(String[] args) {
        System.out.println("=== TEST DE CONNEXION À LA BASE DE DONNÉES ===");
        
        // Test 1: Vérification de la configuration
        System.out.println("\n1. Test de la configuration :");
        System.out.println("   Host: " + DatabaseConfig.getHost());
        System.out.println("   Database: " + DatabaseConfig.getDatabaseName());
        System.out.println("   User: " + DatabaseConfig.getUsername());
        System.out.println("   URL: " + DatabaseConfig.getUrl());
        
        // Test 2: Test de connexion
        System.out.println("\n2. Test de connexion :");
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection conn = connectDB.getConn();
            
            if (conn != null && !conn.isClosed()) {
                System.out.println("   ✅ Connexion établie avec succès !");
                
                // Test 3: Test de requête simple
                System.out.println("\n3. Test de requête simple :");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1 as test");
                
                if (rs.next()) {
                    System.out.println("   ✅ Requête exécutée avec succès ! Résultat: " + rs.getInt("test"));
                }
                
                rs.close();
                stmt.close();
                
            } else {
                System.out.println("   ❌ Échec de la connexion !");
            }
            
        } catch (Exception e) {
            System.out.println("   ❌ Erreur lors du test de connexion: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n=== FIN DU TEST ===");
    }
}
