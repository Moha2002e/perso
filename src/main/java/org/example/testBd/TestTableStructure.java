package org.example.testBd;

import org.example.model.BD.ConnectDB;

import java.sql.ResultSet;
import java.sql.Statement;

public class TestTableStructure {
    
    public static void main(String[] args) {
        System.out.println("=== TEST DE LA STRUCTURE DES TABLES ===");
        
        try {
            ConnectDB connectDB = new ConnectDB();
            
            // Test 1: Structure de la table doctors
            System.out.println("\n1. Structure de la table 'doctors' :");
            Statement stmt = connectDB.getConn().createStatement();
            ResultSet rs = stmt.executeQuery("DESCRIBE doctors");
            
            System.out.println("   Colonnes de la table doctors :");
            while (rs.next()) {
                String columnName = rs.getString("Field");
                String columnType = rs.getString("Type");
                System.out.println("   - " + columnName + " (" + columnType + ")");
            }
            rs.close();
            
            // Test 2: Structure de la table consultations
            System.out.println("\n2. Structure de la table 'consultations' :");
            rs = stmt.executeQuery("DESCRIBE consultations");
            
            System.out.println("   Colonnes de la table consultations :");
            while (rs.next()) {
                String columnName = rs.getString("Field");
                String columnType = rs.getString("Type");
                System.out.println("   - " + columnName + " (" + columnType + ")");
            }
            rs.close();
            
            // Test 3: Contenu de la table doctors
            System.out.println("\n3. Contenu de la table 'doctors' :");
            rs = stmt.executeQuery("SELECT * FROM doctors LIMIT 3");
            
            System.out.println("   Premiers médecins :");
            while (rs.next()) {
                System.out.println("   - ID: " + rs.getInt("id") + 
                                 ", Nom: " + rs.getString("last_name") + 
                                 ", Prénom: " + rs.getString("first_name"));
            }
            rs.close();
            
            stmt.close();
            
        } catch (Exception e) {
            System.out.println("   ❌ Erreur: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n=== FIN DU TEST ===");
    }
}
