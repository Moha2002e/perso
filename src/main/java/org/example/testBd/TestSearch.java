package org.example.testBd;

import org.example.model.dao.ConsultationDAO;
import org.example.model.viewmodel.ConsultationSearchVM;

import java.util.ArrayList;

public class TestSearch {
    
    public static void main(String[] args) {
        System.out.println("=== TEST DE RECHERCHE DE CONSULTATIONS ===");
        
        try {
            ConsultationDAO consultationDAO = new ConsultationDAO();
            
            // Test 1: Chargement de toutes les consultations
            System.out.println("\n1. Toutes les consultations :");
            ArrayList<org.example.model.entity.Consultation> allConsultations = consultationDAO.load();
            System.out.println("   Nombre total de consultations: " + allConsultations.size());
            
            // Test 2: Recherche par nom de patient
            System.out.println("\n2. Recherche par nom de patient :");
            ConsultationSearchVM searchVM = new ConsultationSearchVM();
            searchVM.setPatientName("Dupont");
            ArrayList<org.example.model.entity.Consultation> patientConsultations = consultationDAO.load(searchVM);
            System.out.println("   Consultations trouvées pour 'Dupont': " + patientConsultations.size());
            
            // Test 3: Recherche par nom de médecin
            System.out.println("\n3. Recherche par nom de médecin :");
            searchVM = new ConsultationSearchVM();
            searchVM.setDoctorName("Martin");
            ArrayList<org.example.model.entity.Consultation> doctorConsultations = consultationDAO.load(searchVM);
            System.out.println("   Consultations trouvées pour médecin 'Martin': " + doctorConsultations.size());
            
            // Test 4: Recherche par spécialité
            System.out.println("\n4. Recherche par spécialité :");
            searchVM = new ConsultationSearchVM();
            searchVM.setSpecialityName("Cardiologie");
            ArrayList<org.example.model.entity.Consultation> specialityConsultations = consultationDAO.load(searchVM);
            System.out.println("   Consultations trouvées pour 'Cardiologie': " + specialityConsultations.size());
            
            // Test 5: Recherche par date
            System.out.println("\n5. Recherche par date :");
            searchVM = new ConsultationSearchVM();
            searchVM.setDateFrom("2024-01-01");
            searchVM.setDateTo("2024-12-31");
            ArrayList<org.example.model.entity.Consultation> dateConsultations = consultationDAO.load(searchVM);
            System.out.println("   Consultations trouvées pour 2024: " + dateConsultations.size());
            
            // Test 6: Recherche combinée
            System.out.println("\n6. Recherche combinée :");
            searchVM = new ConsultationSearchVM();
            searchVM.setPatientName("Dupont");
            searchVM.setDateFrom("2024-01-01");
            ArrayList<org.example.model.entity.Consultation> combinedConsultations = consultationDAO.load(searchVM);
            System.out.println("   Consultations combinées trouvées: " + combinedConsultations.size());
            
        } catch (Exception e) {
            System.out.println("   ❌ Erreur lors des tests de recherche: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n=== FIN DES TESTS DE RECHERCHE ===");
    }
}
