package com.spring_boot.backend.restapi.entities;

import java.sql.Date;
//import java.util.List;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;

@Entity
public class jobs {

    @Id
    @GeneratedValue
    private int job_id;
    private String job_role;
    private String location;
    private Date apply_by;
    private int ctc;
    private float cgpa;
    private int experiance;
    private String descrption;
    private String emp_id;

    //@ManyToOne(targetEntity = emp_login.class,cascade = CascadeType.ALL)
    //@JoinColumn(name="emp_id",referencedColumnName = "emp_id")
    //private List<emp_login> emp_login;

    public jobs(){}

    public jobs(int job_id, String job_role, String location, Date apply_by, int ctc, float cgpa, int experiance,
            String descrption, String emp_id) {
        this.job_id = job_id;
        this.job_role = job_role;
        this.location = location;
        this.apply_by = apply_by;
        this.ctc = ctc;
        this.cgpa = cgpa;
        this.experiance = experiance;
        this.descrption = descrption;
        this.emp_id = emp_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getJob_role() {
        return job_role;
    }

    public void setJob_role(String job_role) {
        this.job_role = job_role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getApply_by() {
        return apply_by;
    }

    public void setApply_by(Date apply_by) {
        this.apply_by = apply_by;
    }

    public int getCtc() {
        return ctc;
    }

    public void setCtc(int ctc) {
        this.ctc = ctc;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public int getExperiance() {
        return experiance;
    }

    public void setExperiance(int experiance) {
        this.experiance = experiance;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_login(String emp_id) {
        this.emp_id = emp_id;
    }

    
    
    
}
