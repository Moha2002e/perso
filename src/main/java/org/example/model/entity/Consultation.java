package org.example.model.entity;

import java.io.Serializable;

public class Consultation implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id ;
    private Integer doctor_id ;
    private Integer patient_id ;
    private String date ;
    private String hour ;
    private String reason ;


    public Consultation(){

    }
    public Consultation(Integer id, Integer doctor_id, Integer patient_id, String date, String hour, String reason) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.date = date;
        this.hour = hour;
        this.reason = reason;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getDoctor_id() {
        return doctor_id;
    }
    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }
    public Integer getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getHour() {
        return hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", doctor_id=" + doctor_id + ", patient_id=" + patient_id + ", date=" + date + ", hour=" + hour + ", reason=" + reason + '}';
    }
}
