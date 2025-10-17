package org.example.testBd;

import org.example.model.dao.DAOManager;

public class TestDAOManager {
    
    public static void main(String[] args) {
        System.out.println("=== TEST DU DAO MANAGER (SINGLETON) ===");
        
        // Test 1: Vérification que les instances sont uniques
        System.out.println("\n1. Test des instances uniques :");
        
        DAOManager manager1 = DAOManager.getInstance();
        DAOManager manager2 = DAOManager.getInstance();
        
        if (manager1 == manager2) {
            System.out.println("   ✅ DAOManager est bien un singleton");
        } else {
            System.out.println("   ❌ DAOManager n'est pas un singleton");
        }
        
        // Test 2: Vérification que les DAO sont uniques
        System.out.println("\n2. Test des DAO uniques :");
        
        var consultationDAO1 = manager1.getConsultationDAO();
        var consultationDAO2 = manager2.getConsultationDAO();
        
        if (consultationDAO1 == consultationDAO2) {
            System.out.println("   ✅ ConsultationDAO est bien unique");
        } else {
            System.out.println("   ❌ ConsultationDAO n'est pas unique");
        }
        
        var doctorDAO1 = manager1.getDoctorDAO();
        var doctorDAO2 = manager2.getDoctorDAO();
        
        if (doctorDAO1 == doctorDAO2) {
            System.out.println("   ✅ DoctorDAO est bien unique");
        } else {
            System.out.println("   ❌ DoctorDAO n'est pas unique");
        }
        
        var patientDAO1 = manager1.getPatientDAO();
        var patientDAO2 = manager2.getPatientDAO();
        
        if (patientDAO1 == patientDAO2) {
            System.out.println("   ✅ PatientDAO est bien unique");
        } else {
            System.out.println("   ❌ PatientDAO n'est pas unique");
        }
        
        var specialityDAO1 = manager1.getSpecialityDAO();
        var specialityDAO2 = manager2.getSpecialityDAO();
        
        if (specialityDAO1 == specialityDAO2) {
            System.out.println("   ✅ SpecialityDAO est bien unique");
        } else {
            System.out.println("   ❌ SpecialityDAO n'est pas unique");
        }
        
        // Test 3: Test de fonctionnement des DAO
        System.out.println("\n3. Test de fonctionnement des DAO :");
        
        try {
            var consultations = consultationDAO1.load();
            System.out.println("   ✅ ConsultationDAO fonctionne - " + consultations.size() + " consultations");
            
            var doctors = doctorDAO1.load();
            System.out.println("   ✅ DoctorDAO fonctionne - " + doctors.size() + " médecins");
            
            var patients = patientDAO1.load();
            System.out.println("   ✅ PatientDAO fonctionne - " + patients.size() + " patients");
            
            var specialities = specialityDAO1.load();
            System.out.println("   ✅ SpecialityDAO fonctionne - " + specialities.size() + " spécialités");
            
        } catch (Exception e) {
            System.out.println("   ❌ Erreur lors des tests: " + e.getMessage());
        }
        
        System.out.println("\n=== FIN DU TEST ===");
    }
}
