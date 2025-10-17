package org.example.model.dao;

import org.example.model.BD.ConnectDB;
import org.example.model.entity.Doctor;
import org.example.model.viewmodel.DoctorSearchVM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDAO {
    private ArrayList<Doctor> doctors;
    
    public DoctorDAO(){
        doctors = new ArrayList<>();
    }
    
    public ArrayList<Doctor> getAllDoctors(){
        return doctors;
    }
    
    public Doctor getDoctorById(int id){
        try {
            String query = "SELECT d.*, s.name as speciality_name FROM doctors d " +
                    "LEFT JOIN specialties s ON d.speciality_id = s.id " +
                    "WHERE d.id = ?";
            PreparedStatement ps = ConnectDB.getConn().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                // Note: specialite_id column doesn't exist in the table
                // doctor.setSpecialite_id(rs.getInt("specialite_id"));
                doctor.setLast_name(rs.getString("last_name"));
                doctor.setFirst_name(rs.getString("first_name"));
                rs.close();
                ps.close();
                return doctor;
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).warning(e.getMessage());
        }
        return null;
    }
    
    public ArrayList<Doctor> load(){
        return this.load(null);
    }
    
    public ArrayList<Doctor> load(DoctorSearchVM searchVM){
        ArrayList<Doctor> doctors = new ArrayList<>();
        try{
            String query = "SELECT d.* FROM doctors d WHERE 1=1 ";
            
            if(searchVM != null){
                if(searchVM.getLastName() != null && !searchVM.getLastName().isEmpty()){
                    query += "and d.last_name like ? ";
                }
                if(searchVM.getFirstName() != null && !searchVM.getFirstName().isEmpty()){
                    query += "and d.first_name like ? ";
                }
                // Note: Speciality search removed since doctors table doesn't have speciality_id column
                // if(searchVM.getSpecialityName() != null && !searchVM.getSpecialityName().isEmpty()){
                //     query += "and s.name like ? ";
                // }
            }
            
            query += "ORDER BY d.last_name, d.first_name";
            
            PreparedStatement ps = ConnectDB.getConn().prepareStatement(query);
            
            if (searchVM != null) {
                int paramIndex = 1;
                if(searchVM.getLastName() != null && !searchVM.getLastName().isEmpty()){
                    ps.setString(paramIndex++, "%" + searchVM.getLastName() + "%");
                }
                if(searchVM.getFirstName() != null && !searchVM.getFirstName().isEmpty()){
                    ps.setString(paramIndex++, "%" + searchVM.getFirstName() + "%");
                }
                // Note: Speciality search removed since doctors table doesn't have speciality_id column
                // if(searchVM.getSpecialityName() != null && !searchVM.getSpecialityName().isEmpty()){
                //     ps.setString(paramIndex++, "%" + searchVM.getSpecialityName() + "%");
                // }
            }
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                // Note: specialite_id column doesn't exist in the table
                // doctor.setSpecialite_id(rs.getInt("specialite_id"));
                doctor.setLast_name(rs.getString("last_name"));
                doctor.setFirst_name(rs.getString("first_name"));
                
                doctors.add(doctor);
            }
            
            rs.close();
            ps.close();
            
        } catch (SQLException e){
            Logger.getLogger(DoctorDAO.class.getName()).warning(e.getMessage());
        }
        
        return doctors;
    }
    
    public void save(Doctor doctor){
        try{
            String sql;
            if (doctor != null) {
                if(doctor.getId() != null) { // UPDATE
                    sql = "UPDATE doctors SET " +
                            "last_name = ?, " +
                            "first_name = ? " +
                            "WHERE id = ?";
                    PreparedStatement pStmt = ConnectDB.getConn().prepareStatement(sql);
                    pStmt.setString(1, doctor.getLast_name());
                    pStmt.setString(2, doctor.getFirst_name());
                    pStmt.setInt(3, doctor.getId());
                    pStmt.executeUpdate();
                    pStmt.close();
                } else { // CREATE
                    sql = "INSERT INTO doctors (" +
                            "last_name, " +
                            "first_name " +
                            ") VALUES (" +
                            "?, " +
                            "? " +
                            ")";
                    PreparedStatement pStmt = ConnectDB.getConn().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    pStmt.setString(1, doctor.getLast_name());
                    pStmt.setString(2, doctor.getFirst_name());
                    pStmt.executeUpdate();
                    ResultSet rs = pStmt.getGeneratedKeys();
                    rs.next();
                    doctor.setId((int) rs.getLong(1));
                    rs.close();
                    pStmt.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(Doctor entity) {
        if (entity != null && entity.getId() != null) {
            this.delete(entity.getId());
        }
    }
    
    public void delete(Integer id) {
        if (id != null) {
            try {
                String sql = "DELETE FROM doctors WHERE id = ?";
                PreparedStatement stmt = ConnectDB.getConn().prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
