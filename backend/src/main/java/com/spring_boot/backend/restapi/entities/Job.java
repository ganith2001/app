package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.sql.Date;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;


@Entity
public class Job {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
    private String job_id;

    private String job_role;
    private int ctc;
    private String location;
    private Date apply_by;
    private float cgpa;
    private int experiance;
    private String description;

    @OneToOne
    @JoinColumn(name="empid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private recruiterSignup recruiterSignup;

    @OneToMany(targetEntity=RequiredSkills.class,cascade=CascadeType.ALL)
    @JoinColumn(name="job_id",referencedColumnName="job_id")
    private List<RequiredSkills> requiredSkills;

     public Job(){}

    public Job(String job_id){
        this.job_id=job_id;
    }


    public Job(String job_role, int ctc, String location, Date apply_by, float cgpa,int experiance, String description,
            List<com.spring_boot.backend.restapi.entities.RequiredSkills> requiredSkills) {
        this.job_role = job_role;
        this.ctc = ctc;
        this.location = location;
        this.apply_by = apply_by;
        this.cgpa = cgpa;
        this.experiance=experiance;
        this.description = description;
        this.requiredSkills = requiredSkills;
    }



    public Job(String job_role, int ctc, String location, Date apply_by, float cgpa,int experiance, String description,
            com.spring_boot.backend.restapi.entities.recruiterSignup recruiterSignup) {
        this.job_role = job_role;
        this.ctc = ctc;
        this.location = location;
        this.apply_by = apply_by;
        this.cgpa = cgpa;
        this.experiance=experiance;
        this.description = description;
        this.recruiterSignup = recruiterSignup;
    }

    public Job(String job_role, int ctc, String location, Date apply_by, float cgpa,int experiance, String description) {
        this.job_role = job_role;
        this.ctc = ctc;
        this.location = location;
        this.apply_by = apply_by;
        this.cgpa = cgpa;
        this.experiance=experiance;
        this.description = description;
     
    }


    public String getJob_role() {
        return job_role;
    }

    public void setJob_role(String job_role) {
        this.job_role = job_role;
    }

    public int getCtc() {
        return ctc;
    }

    public void setCtc(int ctc) {
        this.ctc = ctc;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public recruiterSignup getRecruiterSignup() {
        return recruiterSignup;
    }

    public void setRecruiterSignup(recruiterSignup recruiterSignup) {
        this.recruiterSignup = recruiterSignup;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public List<RequiredSkills> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<RequiredSkills> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    
    

}
