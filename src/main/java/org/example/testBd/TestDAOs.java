package org.example.testBd;

import org.example.model.dao.*;
import org.example.model.entity.*;

import java.util.ArrayList;

public class TestDAOs {
    
    public static void main(String[] args) {
        System.out.println("=== TEST DES DAOs ===");
        
        // Test 1: Test SpecialityDAO
        System.out.println("\n1. Test SpecialityDAO :");
        testSpecialityDAO();
        
        // Test 2: Test DoctorDAO
        System.out.println("\n2. Test DoctorDAO :");
        testDoctorDAO();
        
        // Test 3: Test PatientDAO
        System.out.println("\n3. Test PatientDAO :");
        testPatientDAO();
        
        // Test 4: Test ConsultationDAO
        System.out.println("\n4. Test ConsultationDAO :");
        testConsultationDAO();
        
        System.out.println("\n=== FIN DES TESTS ===");
    }
    
    private static void testSpecialityDAO() {
        try {
            SpecialityDAO specialityDAO = new SpecialityDAO();
            ArrayList<Speciality> specialities = specialityDAO.load();
            
            System.out.println("   ✅ Chargement des spécialités réussi !");
            System.out.println("   Nombre de spécialités: " + specialities.size());
            
            for (Speciality s : specialities) {
                System.out.println("   - " + s.toString());
            }
            
        } catch (Exception e) {
            System.out.println("   ❌ Erreur SpecialityDAO: " + e.getMessage());
        }
    }
    
    private static void testDoctorDAO() {
        try {
            DoctorDAO doctorDAO = new DoctorDAO();
            ArrayList<Doctor> doctors = doctorDAO.load();
            
            System.out.println("   ✅ Chargement des médecins réussi !");
            System.out.println("   Nombre de médecins: " + doctors.size());
            
            for (Doctor d : doctors) {
                System.out.println("   - " + d.toString());
            }
            
        } catch (Exception e) {
            System.out.println("   ❌ Erreur DoctorDAO: " + e.getMessage());
        }
    }
    
    private static void testPatientDAO() {
        try {
            PatientDAO patientDAO = new PatientDAO();
            ArrayList<Patient> patients = patientDAO.load();
            
            System.out.println("   ✅ Chargement des patients réussi !");
            System.out.println("   Nombre de patients: " + patients.size());
            
            for (Patient p : patients) {
                System.out.println("   - " + p.toString());
            }
            
        } catch (Exception e) {
            System.out.println("   ❌ Erreur PatientDAO: " + e.getMessage());
        }
    }
    
    private static void testConsultationDAO() {
        try {
            ConsultationDAO consultationDAO = new ConsultationDAO();
            ArrayList<Consultation> consultations = consultationDAO.load();
            
            System.out.println("   ✅ Chargement des consultations réussi !");
            System.out.println("   Nombre de consultations: " + consultations.size());
            
            for (Consultation c : consultations) {
                System.out.println("   - " + c.toString());
            }
            
        } catch (Exception e) {
            System.out.println("   ❌ Erreur ConsultationDAO: " + e.getMessage());
        }
    }
}
