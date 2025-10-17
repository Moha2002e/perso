package org.example.testBd;

import org.example.model.dao.*;
import org.example.model.entity.*;

public class TestCRUD {
    
    public static void main(String[] args) {
        System.out.println("=== TEST DES OPÉRATIONS CRUD ===");
        
        // Test 1: Test CRUD Speciality
        System.out.println("\n1. Test CRUD Speciality :");
        testSpecialityCRUD();
        
        // Test 2: Test CRUD Patient
        System.out.println("\n2. Test CRUD Patient :");
        testPatientCRUD();
        
        System.out.println("\n=== FIN DES TESTS CRUD ===");
    }
    
    private static void testSpecialityCRUD() {
        try {
            SpecialityDAO specialityDAO = new SpecialityDAO();
            
            // CREATE - Créer une nouvelle spécialité
            System.out.println("   Test CREATE :");
            Speciality newSpeciality = new Speciality();
            newSpeciality.setName("Test Cardiologie");
            specialityDAO.save(newSpeciality);
            System.out.println("   ✅ Spécialité créée avec ID: " + newSpeciality.getId());
            
            // READ - Lire la spécialité créée
            System.out.println("   Test READ :");
            Speciality foundSpeciality = specialityDAO.getSpecialityById(newSpeciality.getId());
            if (foundSpeciality != null) {
                System.out.println("   ✅ Spécialité trouvée: " + foundSpeciality.toString());
            } else {
                System.out.println("   ❌ Spécialité non trouvée");
            }
            
            // UPDATE - Modifier la spécialité
            System.out.println("   Test UPDATE :");
            newSpeciality.setName("Test Cardiologie Modifiée");
            specialityDAO.save(newSpeciality);
            System.out.println("   ✅ Spécialité mise à jour");
            
            // DELETE - Supprimer la spécialité
            System.out.println("   Test DELETE :");
            specialityDAO.delete(newSpeciality);
            System.out.println("   ✅ Spécialité supprimée");
            
        } catch (Exception e) {
            System.out.println("   ❌ Erreur CRUD Speciality: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void testPatientCRUD() {
        try {
            PatientDAO patientDAO = new PatientDAO();
            
            // CREATE - Créer un nouveau patient
            System.out.println("   Test CREATE :");
            Patient newPatient = new Patient();
            newPatient.setLast_name("Dupont");
            newPatient.setFirst_name("Jean");
            newPatient.setBirth_date("1990-01-01");
            patientDAO.save(newPatient);
            System.out.println("   ✅ Patient créé avec ID: " + newPatient.getId());
            
            // READ - Lire le patient créé
            System.out.println("   Test READ :");
            Patient foundPatient = patientDAO.getPatientById(newPatient.getId());
            if (foundPatient != null) {
                System.out.println("   ✅ Patient trouvé: " + foundPatient.toString());
            } else {
                System.out.println("   ❌ Patient non trouvé");
            }
            
            // UPDATE - Modifier le patient
            System.out.println("   Test UPDATE :");
            newPatient.setFirst_name("Jean-Pierre");
            patientDAO.save(newPatient);
            System.out.println("   ✅ Patient mis à jour");
            
            // DELETE - Supprimer le patient
            System.out.println("   Test DELETE :");
            patientDAO.delete(newPatient);
            System.out.println("   ✅ Patient supprimé");
            
        } catch (Exception e) {
            System.out.println("   ❌ Erreur CRUD Patient: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
