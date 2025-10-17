package org.example.model.dao;

/**
 * DAO Manager - Singleton pour gérer les instances uniques des DAO
 * Assure qu'une seule instance de chaque DAO est créée et partagée
 * entre tous les threads du serveur
 */
public class DAOManager {
    private static DAOManager instance = null;
    private static final Object lock = new Object();
    
    // Instances uniques des DAO
    private ConsultationDAO consultationDAO;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private SpecialityDAO specialityDAO;
    
    private DAOManager() {
        // Constructeur privé pour empêcher l'instanciation directe
    }
    
    /**
     * Retourne l'instance unique du DAOManager (Singleton)
     * Thread-safe avec double-checked locking
     */
    public static DAOManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DAOManager();
                }
            }
        }
        return instance;
    }
    
    /**
     * Retourne l'instance unique de ConsultationDAO
     */
    public ConsultationDAO getConsultationDAO() {
        if (consultationDAO == null) {
            synchronized (lock) {
                if (consultationDAO == null) {
                    consultationDAO = new ConsultationDAO();
                }
            }
        }
        return consultationDAO;
    }
    
    /**
     * Retourne l'instance unique de DoctorDAO
     */
    public DoctorDAO getDoctorDAO() {
        if (doctorDAO == null) {
            synchronized (lock) {
                if (doctorDAO == null) {
                    doctorDAO = new DoctorDAO();
                }
            }
        }
        return doctorDAO;
    }
    
    /**
     * Retourne l'instance unique de PatientDAO
     */
    public PatientDAO getPatientDAO() {
        if (patientDAO == null) {
            synchronized (lock) {
                if (patientDAO == null) {
                    patientDAO = new PatientDAO();
                }
            }
        }
        return patientDAO;
    }
    
    /**
     * Retourne l'instance unique de SpecialityDAO
     */
    public SpecialityDAO getSpecialityDAO() {
        if (specialityDAO == null) {
            synchronized (lock) {
                if (specialityDAO == null) {
                    specialityDAO = new SpecialityDAO();
                }
            }
        }
        return specialityDAO;
    }
}
