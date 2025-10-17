package org.example.model.dao;

import org.example.model.BD.ConnectDB;
import org.example.model.entity.Patient;
import org.example.model.viewmodel.PatientSearchVM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientDAO {
    private ArrayList<Patient> patients;
    
    public PatientDAO(){
        patients = new ArrayList<>();
    }
    
    public ArrayList<Patient> getAllPatients(){
        return patients;
    }
    
    public Patient getPatientById(int id){
        try {
            String query = "SELECT * FROM patients WHERE id = ?";
            PreparedStatement ps = ConnectDB.getConn().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setLast_name(rs.getString("last_name"));
                patient.setFirst_name(rs.getString("first_name"));
                patient.setBirth_date(rs.getString("birth_date"));
                rs.close();
                ps.close();
                return patient;
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(PatientDAO.class.getName()).warning(e.getMessage());
        }
        return null;
    }
    
    public ArrayList<Patient> load(){
        return this.load(null);
    }
    
    public ArrayList<Patient> load(PatientSearchVM searchVM){
        ArrayList<Patient> patients = new ArrayList<>();
        try{
            String query = "SELECT p.* FROM patients p WHERE 1=1 ";
            
            if(searchVM != null){
                if(searchVM.getLastName() != null && !searchVM.getLastName().isEmpty()){
                    query += "and p.last_name like ? ";
                }
                if(searchVM.getFirstName() != null && !searchVM.getFirstName().isEmpty()){
                    query += "and p.first_name like ? ";
                }
                if(searchVM.getBirthDateFrom() != null && !searchVM.getBirthDateFrom().isEmpty()){
                    query += "and p.birth_date >= ? ";
                }
                if(searchVM.getBirthDateTo() != null && !searchVM.getBirthDateTo().isEmpty()){
                    query += "and p.birth_date <= ? ";
                }
            }
            
            query += "ORDER BY p.last_name, p.first_name";
            
            PreparedStatement ps = ConnectDB.getConn().prepareStatement(query);
            
            if (searchVM != null) {
                int paramIndex = 1;
                if(searchVM.getLastName() != null && !searchVM.getLastName().isEmpty()){
                    ps.setString(paramIndex++, "%" + searchVM.getLastName() + "%");
                }
                if(searchVM.getFirstName() != null && !searchVM.getFirstName().isEmpty()){
                    ps.setString(paramIndex++, "%" + searchVM.getFirstName() + "%");
                }
                if(searchVM.getBirthDateFrom() != null && !searchVM.getBirthDateFrom().isEmpty()){
                    ps.setString(paramIndex++, searchVM.getBirthDateFrom());
                }
                if(searchVM.getBirthDateTo() != null && !searchVM.getBirthDateTo().isEmpty()){
                    ps.setString(paramIndex++, searchVM.getBirthDateTo());
                }
            }
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setLast_name(rs.getString("last_name"));
                patient.setFirst_name(rs.getString("first_name"));
                patient.setBirth_date(rs.getString("birth_date"));
                
                patients.add(patient);
            }
            
            rs.close();
            ps.close();
            
        } catch (SQLException e){
            Logger.getLogger(PatientDAO.class.getName()).warning(e.getMessage());
        }
        
        return patients;
    }
    
    public void save(Patient patient){
        try{
            String sql;
            if (patient != null) {
                if(patient.getId() != null) { // UPDATE
                    sql = "UPDATE patients SET " +
                            "last_name = ?, " +
                            "first_name = ?, " +
                            "birth_date = ? " +
                            "WHERE id = ?";
                    PreparedStatement pStmt = ConnectDB.getConn().prepareStatement(sql);
                    pStmt.setString(1, patient.getLast_name());
                    pStmt.setString(2, patient.getFirst_name());
                    pStmt.setString(3, patient.getBirth_date());
                    pStmt.setInt(4, patient.getId());
                    pStmt.executeUpdate();
                    pStmt.close();
                } else { // CREATE
                    sql = "INSERT INTO patients (" +
                            "last_name, " +
                            "first_name, " +
                            "birth_date " +
                            ") VALUES (" +
                            "?, " +
                            "?, " +
                            "? " +
                            ")";
                    PreparedStatement pStmt = ConnectDB.getConn().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    pStmt.setString(1, patient.getLast_name());
                    pStmt.setString(2, patient.getFirst_name());
                    pStmt.setString(3, patient.getBirth_date());
                    pStmt.executeUpdate();
                    ResultSet rs = pStmt.getGeneratedKeys();
                    rs.next();
                    patient.setId((int) rs.getLong(1));
                    rs.close();
                    pStmt.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(Patient entity) {
        if (entity != null && entity.getId() != null) {
            this.delete(entity.getId());
        }
    }
    
    public void delete(Integer id) {
        if (id != null) {
            try {
                String sql = "DELETE FROM patients WHERE id = ?";
                PreparedStatement stmt = ConnectDB.getConn().prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
