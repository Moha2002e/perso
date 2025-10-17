package org.example.model.entity;

import java.io.Serializable;

public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id ;
    private Integer specialite_id ;
    private String last_name ;
    private String first_name ;

    public Doctor(){

    }
    public Doctor(Integer id, Integer specialite_id, String last_name, String first_name) {
        this.id = id;
        this.specialite_id = specialite_id;
        this.last_name = last_name;
        this.first_name = first_name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSpecialite_id() {
        return specialite_id;
    }
    public void setSpecialite_id(Integer specialite_id) {
        this.specialite_id = specialite_id;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    @Override
    public String toString() {
        return "Doctor{" + "id=" + id + ", specialite_id=" + specialite_id + ", last_name=" + last_name + ", first_name=" + first_name + '}';
    }


}
