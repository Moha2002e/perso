package org.example.client;

import org.example.server.protocol.CAPCommand;
import org.example.server.protocol.CAPResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Client de test simple pour tester le serveur
 */
public class TestClient {
    
    public static void main(String[] args) {
        try {
            // Connexion au serveur
            Socket socket = new Socket("localhost", 8080);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            // Test 1: Login
            System.out.println("=== Test 1: Login ===");
            CAPCommand loginCmd = new CAPCommand(CAPCommand.CommandType.LOGIN, "test_user");
            oos.writeObject(loginCmd);
            oos.flush();
            
            CAPResponse response = (CAPResponse) ois.readObject();
            System.out.println("Réponse: " + response.getStatus() + " - " + response.getMessage());
            
            // Test 2: Get Consultations
            System.out.println("\n=== Test 2: Get Consultations ===");
            CAPCommand getConsultationsCmd = new CAPCommand(CAPCommand.CommandType.GET_CONSULTATIONS, null);
            oos.writeObject(getConsultationsCmd);
            oos.flush();
            
            response = (CAPResponse) ois.readObject();
            System.out.println("Réponse: " + response.getStatus() + " - " + response.getMessage());
            System.out.println("Données: " + response.getData());
            
            // Test 3: Get Doctors
            System.out.println("\n=== Test 3: Get Doctors ===");
            CAPCommand getDoctorsCmd = new CAPCommand(CAPCommand.CommandType.GET_DOCTORS, null);
            oos.writeObject(getDoctorsCmd);
            oos.flush();
            
            response = (CAPResponse) ois.readObject();
            System.out.println("Réponse: " + response.getStatus() + " - " + response.getMessage());
            System.out.println("Données: " + response.getData());
            
            // Test 4: Logout
            System.out.println("\n=== Test 4: Logout ===");
            CAPCommand logoutCmd = new CAPCommand(CAPCommand.CommandType.LOGOUT, null);
            oos.writeObject(logoutCmd);
            oos.flush();
            
            response = (CAPResponse) ois.readObject();
            System.out.println("Réponse: " + response.getStatus() + " - " + response.getMessage());
            
            // Fermeture
            oos.close();
            ois.close();
            socket.close();
            
            System.out.println("\n=== Tests terminés avec succès ===");
            
        } catch (Exception e) {
            System.err.println("Erreur lors des tests: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
