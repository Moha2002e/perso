package org.example.model.dao;

import org.example.model.BD.ConnectDB;
import org.example.model.entity.Speciality;
import org.example.model.viewmodel.SpecialitySearchVM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpecialityDAO {
    private ArrayList<Speciality> specialities;
    
    public SpecialityDAO(){
        specialities = new ArrayList<>();
    }
    
    public ArrayList<Speciality> getAllSpecialities(){
        return specialities;
    }
    
    public Speciality getSpecialityById(int id){
        try {
            String query = "SELECT * FROM specialties WHERE id = ?";
            PreparedStatement ps = ConnectDB.getConn().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Speciality speciality = new Speciality();
                speciality.setId(rs.getInt("id"));
                speciality.setName(rs.getString("name"));
                rs.close();
                ps.close();
                return speciality;
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(SpecialityDAO.class.getName()).warning(e.getMessage());
        }
        return null;
    }
    
    public ArrayList<Speciality> load(){
        return this.load(null);
    }
    
    public ArrayList<Speciality> load(SpecialitySearchVM searchVM){
        ArrayList<Speciality> specialities = new ArrayList<>();
        try{
            String query = "SELECT s.* FROM specialties s WHERE 1=1 ";
            
            if(searchVM != null){
                if(searchVM.getName() != null && !searchVM.getName().isEmpty()){
                    query += "and s.name like ? ";
                }
            }
            
            query += "ORDER BY s.name";
            
            PreparedStatement ps = ConnectDB.getConn().prepareStatement(query);
            
            if (searchVM != null) {
                if(searchVM.getName() != null && !searchVM.getName().isEmpty()){
                    ps.setString(1, "%" + searchVM.getName() + "%");
                }
            }
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Speciality speciality = new Speciality();
                speciality.setId(rs.getInt("id"));
                speciality.setName(rs.getString("name"));
                
                specialities.add(speciality);
            }
            
            rs.close();
            ps.close();
            
        } catch (SQLException e){
            Logger.getLogger(SpecialityDAO.class.getName()).warning(e.getMessage());
        }
        
        return specialities;
    }
    
    public void save(Speciality speciality){
        try{
            String sql;
            if (speciality != null) {
                if(speciality.getId() != null) { // UPDATE
                    sql = "UPDATE specialties SET " +
                            "name = ? " +
                            "WHERE id = ?";
                    PreparedStatement pStmt = ConnectDB.getConn().prepareStatement(sql);
                    pStmt.setString(1, speciality.getName());
                    pStmt.setInt(2, speciality.getId());
                    pStmt.executeUpdate();
                    pStmt.close();
                } else { // CREATE
                    sql = "INSERT INTO specialties (" +
                            "name " +
                            ") VALUES (" +
                            "? " +
                            ")";
                    PreparedStatement pStmt = ConnectDB.getConn().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    pStmt.setString(1, speciality.getName());
                    pStmt.executeUpdate();
                    ResultSet rs = pStmt.getGeneratedKeys();
                    rs.next();
                    speciality.setId((int) rs.getLong(1));
                    rs.close();
                    pStmt.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SpecialityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(Speciality entity) {
        if (entity != null && entity.getId() != null) {
            this.delete(entity.getId());
        }
    }
    
    public void delete(Integer id) {
        if (id != null) {
            try {
                String sql = "DELETE FROM specialties WHERE id = ?";
                PreparedStatement stmt = ConnectDB.getConn().prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(SpecialityDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
