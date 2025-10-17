package org.example.testBd;

import java.sql.*;

public class TestBd {

    public static void main(String[] args) {
        // Informations de connexion
        String url = "jdbc:mysql://192.168.0.16/PourStudent";
        String user = "Student";
        String password = "PassStudent1_";

        try {
            // Chargement du driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver chargé avec succès ✅");

            // Connexion à la base de données
            try (Connection con = DriverManager.getConnection(url, user, password)) {
                System.out.println("Connexion à la BD 'PourStudent' établie ✅");

                // Création d'un Statement pour exécuter une requête
                try (Statement stmt = con.createStatement()) {
                    System.out.println("Statement créé ✅");

                    // Exécution d’une requête SELECT
                    try (ResultSet rs = stmt.executeQuery("SELECT * FROM patients")) {
                        System.out.println("Requête SELECT exécutée ✅");
                        System.out.println("Instruction : SELECT * FROM Personnes");

                        // Affichage des métadonnées : nombre et noms des colonnes
                        ResultSetMetaData metaData = rs.getMetaData();
                        int columnCount = metaData.getColumnCount();
                        System.out.println("Nombre de colonnes = " + columnCount);

                        for (int j = 1; j <= columnCount; j++) {
                            System.out.print(metaData.getColumnName(j) + "\t");
                        }
                        System.out.println();

                        // Affichage du contenu du ResultSet
                        while (rs.next()) {
                            for (int j = 1; j <= columnCount; j++) {
                                System.out.print(rs.getObject(j) + "\t");
                            }
                            System.out.println();
                        }
                    }
                }

                // Autre exemple de requête : compter le nombre de tuples
                try (Statement instruc = con.createStatement()) {
                    boolean retour = instruc.execute("SELECT COUNT(*) FROM patients");
                    System.out.println("Instruction SELECT COUNT(*) exécutée ✅");
                    System.out.println("Retour = " + retour);

                    try (ResultSet rsc = instruc.getResultSet()) {
                        if (rsc == null) {
                            System.out.println("Pas de ResultSet retourné ❌");
                        } else {
                            System.out.println("ResultSet récupéré ✅");
                            if (rsc.next()) {
                                int nbre = rsc.getInt(1);
                                System.out.println("Nombre de tuples = " + nbre);
                            }
                        }
                    }
                }

            } // Connection se ferme automatiquement ici

        } catch (ClassNotFoundException ex) {
            System.out.println("❌ Erreur : driver non trouvé - " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("❌ Erreur SQL : " + ex.getMessage());
        }
    }
}
