package org.example.server.protocol;

import java.io.Serializable;

/**
 * Commande CAP (Consultation Access Protocol) - Objet sérialisé
 * Représente une commande envoyée par le client au serveur
 */
public class CAPCommand implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum CommandType {
        LOGIN,
        ADD_CONSULTATION,
        GET_CONSULTATIONS,
        SEARCH_CONSULTATIONS,
        GET_DOCTORS,
        SEARCH_DOCTORS,
        GET_PATIENTS,
        SEARCH_PATIENTS,
        GET_SPECIALITIES,
        SEARCH_SPECIALITIES,
        LOGOUT
    }
    
    private CommandType type;
    private Object data;
    private String sessionId;
    
    public CAPCommand() {
    }
    
    public CAPCommand(CommandType type, Object data) {
        this.type = type;
        this.data = data;
    }
    
    public CAPCommand(CommandType type, Object data, String sessionId) {
        this.type = type;
        this.data = data;
        this.sessionId = sessionId;
    }
    
    // Getters and Setters
    public CommandType getType() {
        return type;
    }
    
    public void setType(CommandType type) {
        this.type = type;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    @Override
    public String toString() {
        return "CAPCommand{" +
                "type=" + type +
                ", data=" + data +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
