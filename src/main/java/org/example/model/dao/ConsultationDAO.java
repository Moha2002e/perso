package org.example.model.dao;

import org.example.model.BD.ConnectDB;
import org.example.model.entity.Consultation;
import org.example.model.viewmodel.ConsultationSearchVM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultationDAO {
    private ArrayList<Consultation> consultations;
    
    public ConsultationDAO(){
        consultations = new ArrayList<>();
    }
    public ArrayList<Consultation> getAllConsultations(){
        return consultations;
    }
    public Consultation getConsultationById(int id){
        try {
            String query = "SELECT c.* FROM consultations c " +
                    "LEFT JOIN patients p ON c.patient_id = p.id " +
                    "LEFT JOIN doctors d ON c.doctor_id = d.id " +
                    "LEFT JOIN specialties s ON d.speciality_id = s.id " +
                    "WHERE c.id = ?";
            PreparedStatement ps = ConnectDB.getConn().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Consultation consultation = new Consultation();
                consultation.setId(rs.getInt("id"));
                consultation.setDoctor_id(rs.getInt("doctor_id"));
                consultation.setPatient_id(rs.getInt("patient_id"));
                consultation.setDate(rs.getString("date"));
                consultation.setHour(rs.getString("hour"));
                consultation.setReason(rs.getString("reason"));
                rs.close();
                ps.close();
                return consultation;
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(ConsultationDAO.class.getName()).warning(e.getMessage());
        }
        return null;
    }

    public  ArrayList<Consultation> load(){
        return this.load(null);
    }
    public ArrayList<Consultation> load(ConsultationSearchVM searchVM){
        ArrayList<Consultation> consultations = new ArrayList<>();
        try{
            String query = "SELECT c.* FROM consultations c " +
                    "LEFT JOIN patients p ON c.patient_id = p.id " +
                    "LEFT JOIN doctors d ON c.doctor_id = d.id " +
                    "WHERE 1=1 ";

            if(searchVM != null){
                if(searchVM.getPatientName() != null && !searchVM.getPatientName().isEmpty()){
                    query += "and p.last_name like ? ";
                }
                if(searchVM.getDoctorName() != null && !searchVM.getDoctorName().isEmpty()){
                    query += "and d.last_name like ? ";
                }
                // Note: Speciality search removed since doctors table doesn't have speciality_id column
                // if(searchVM.getSpecialityName() != null && !searchVM.getSpecialityName().isEmpty()){
                //     query += "and s.name like ? ";
                // }
                if(searchVM.getDateFrom() != null && !searchVM.getDateFrom().isEmpty()){
                    query += "and c.date >= ? ";
                }
                if(searchVM.getDateTo() != null && !searchVM.getDateTo().isEmpty()){
                    query += "and c.date <= ? ";
                }
                if(searchVM.getReason() != null && !searchVM.getReason().isEmpty()){
                    query += "and c.reason like ? ";
                }
            }
            
            query += " order by c.date desc";
            
            PreparedStatement ps = ConnectDB.getConn().prepareStatement(query);
            if (searchVM != null) {
                int paramNumer = 0;
                if(searchVM.getPatientName() != null && !searchVM.getPatientName().isEmpty()){
                    ps.setString(++paramNumer, "%"+searchVM.getPatientName()+"%");
                }
                if(searchVM.getDoctorName() != null && !searchVM.getDoctorName().isEmpty()){
                    ps.setString(++paramNumer, "%"+searchVM.getDoctorName()+"%");
                }
                // Note: Speciality search removed since doctors table doesn't have speciality_id column
                // if(searchVM.getSpecialityName() != null && !searchVM.getSpecialityName().isEmpty()){
                //     ps.setString(++paramNumer, "%"+searchVM.getSpecialityName()+"%");
                // }
                if(searchVM.getDateFrom() != null && !searchVM.getDateFrom().isEmpty()){
                    ps.setString(++paramNumer, searchVM.getDateFrom());
                }
                if(searchVM.getDateTo() != null && !searchVM.getDateTo().isEmpty()){
                    ps.setString(++paramNumer, searchVM.getDateTo());
                }
                if(searchVM.getReason() != null && !searchVM.getReason().isEmpty()){
                    ps.setString(++paramNumer, "%"+searchVM.getReason()+"%");
                }
            }


            ResultSet rs = ps.executeQuery();
            consultations.clear();
            
            while (rs.next()) {
                Consultation consultation = new Consultation();
                consultation.setId(rs.getInt("id"));
                consultation.setDoctor_id(rs.getInt("doctor_id"));
                consultation.setPatient_id(rs.getInt("patient_id"));
                consultation.setDate(rs.getString("date"));
                consultation.setHour(rs.getString("hour"));
                consultation.setReason(rs.getString("reason"));
                
                consultations.add(consultation);
            }
            
            rs.close();
            ps.close();
            
        }catch (SQLException e){
            Logger.getLogger(ConsultationDAO.class.getName()).warning(e.getMessage());
        }
        finally {
            return consultations;
        }
        

    }
    public void save(Consultation consultation){
        try{
            String sql;
            if (consultation != null) {
                if(consultation.getId() != null) { // UPDATE
                    if(consultation.getDoctor_id() == null || consultation.getPatient_id() == null) {
                        return; // ...exception !
                    }
                    sql = "UPDATE consultations SET " +
                            "doctor_id = ?, " +
                            "patient_id = ?, " +
                            "date = ?, " +
                            "hour = ?, " +
                            "reason = ? " +
                            "WHERE id = ?";
                    PreparedStatement pStmt = ConnectDB.getConn().prepareStatement(sql);
                    pStmt.setInt(1, consultation.getDoctor_id());
                    pStmt.setInt(2, consultation.getPatient_id());
                    pStmt.setString(3, consultation.getDate());
                    pStmt.setString(4, consultation.getHour());
                    pStmt.setString(5, consultation.getReason());
                    pStmt.setInt(6, consultation.getId());
                    pStmt.executeUpdate();
                    pStmt.close();
                } else { // CREATE
                    if(consultation.getDoctor_id() == null || consultation.getPatient_id() == null) {
                        return; // ...exception !
                    }
                    sql = "INSERT INTO consultations (" +
                            "doctor_id, " +
                            "patient_id, " +
                            "date, " +
                            "hour, " +
                            "reason " +
                            ") VALUES (" +
                            "?, " +
                            "?, " +
                            "?, " +
                            "?, " +
                            "? " +
                            ")";
                    PreparedStatement pStmt = ConnectDB.getConn().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    pStmt.setInt(1, consultation.getDoctor_id());
                    pStmt.setInt(2, consultation.getPatient_id());
                    pStmt.setString(3, consultation.getDate());
                    pStmt.setString(4, consultation.getHour());
                    pStmt.setString(5, consultation.getReason());
                    pStmt.executeUpdate();
                    ResultSet rs = pStmt.getGeneratedKeys();
                    rs.next();
                    consultation.setId((int) rs.getLong(1));
                    rs.close();
                    pStmt.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(Consultation entity) {
        if (entity != null && entity.getId() != null) {
            this.delete(entity.getId());
        }
    }
    
    public void delete(Integer id) {
        if (id != null) {
            try {
                String sql = "DELETE FROM consultations WHERE id = ?";
                PreparedStatement stmt = ConnectDB.getConn().prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConsultationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
