package com.spring_boot.backend.restapi.model;

import java.sql.Date;
import java.util.List;

public class JobsRequest {
   
    private String job_role;
    private int ctc;
    private String location;
    private Date apply_by;
    private float cgpa;
    private int experiance;
    private String description;
    private String empid;
    private List<String> skills;

    public JobsRequest(){}

    public JobsRequest(String job_role, int ctc, String location, Date apply_by, float cgpa,int experiance, String description,
            String empid, List<String> skills) {
        this.job_role = job_role;
        this.ctc = ctc;
        this.location = location;
        this.apply_by = apply_by;
        this.cgpa = cgpa;
        this.experiance=experiance;
        this.description = description;
        this.empid = empid;
        this.skills = skills;
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

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmp_id() {
        return empid;
    }

    public void setEmp_id(String empid) {
        this.empid = empid;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public int getExperiance() {
        return experiance;
    }

    public void setExperiance(int experiance) {
        this.experiance = experiance;
    }

    

}
