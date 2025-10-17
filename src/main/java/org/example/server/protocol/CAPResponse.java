package org.example.server.protocol;

import java.io.Serializable;
import java.util.List;

/**
 * Réponse CAP (Consultation Access Protocol) - Objet sérialisé
 * Représente une réponse envoyée par le serveur au client
 */
public class CAPResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum Status {
        SUCCESS,
        ERROR,
        UNAUTHORIZED,
        INVALID_COMMAND,
        DATABASE_ERROR
    }
    
    private Status status;
    private String message;
    private Object data;
    private String sessionId;
    private long timestamp;
    
    public CAPResponse() {
        this.timestamp = System.currentTimeMillis();
    }
    
    public CAPResponse(Status status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
    
    public CAPResponse(Status status, String message, Object data) {
        this(status, message);
        this.data = data;
    }
    
    public CAPResponse(Status status, String message, Object data, String sessionId) {
        this(status, message, data);
        this.sessionId = sessionId;
    }
    
    // Méthodes statiques pour créer des réponses communes
    public static CAPResponse success(String message) {
        return new CAPResponse(Status.SUCCESS, message);
    }
    
    public static CAPResponse success(String message, Object data) {
        return new CAPResponse(Status.SUCCESS, message, data);
    }
    
    public static CAPResponse error(String message) {
        return new CAPResponse(Status.ERROR, message);
    }
    
    public static CAPResponse error(String message, Object data) {
        return new CAPResponse(Status.ERROR, message, data);
    }
    
    public static CAPResponse unauthorized(String message) {
        return new CAPResponse(Status.UNAUTHORIZED, message);
    }
    
    public static CAPResponse invalidCommand(String message) {
        return new CAPResponse(Status.INVALID_COMMAND, message);
    }
    
    public static CAPResponse databaseError(String message) {
        return new CAPResponse(Status.DATABASE_ERROR, message);
    }
    
    // Getters and Setters
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
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
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return "CAPResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", sessionId='" + sessionId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
