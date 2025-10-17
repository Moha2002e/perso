package org.example.model.BD;

import org.example.model.config.DatabaseConfig;

import java.sql.*;
import java.util.logging.*;

public class ConnectDB {
    private static Connection conn = null;
    private static final Object lock = new Object();

    public static Connection getConn() {
        synchronized (lock) {
            if (conn == null || isConnectionClosed()) {
                try {
                    String sCon = DatabaseConfig.getUrl();
                    String sUser = DatabaseConfig.getUsername();
                    String sPwd = DatabaseConfig.getPassword();
                    Class.forName(DatabaseConfig.getDriver());
                    conn = DriverManager.getConnection(sCon, sUser, sPwd);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return conn;
        }
    }
    
    private static boolean isConnectionClosed() {
        try {
            return conn == null || conn.isClosed();
        } catch (SQLException e) {
            return true;
        }
    }

    public static void close() {
        synchronized (lock) {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("Closing DB connection");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}