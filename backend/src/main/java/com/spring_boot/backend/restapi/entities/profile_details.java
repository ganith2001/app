package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="userprofile")
public class profile_details {

    @Id
    @GeneratedValue
    private int p_id;
    private String phone;
    private String gender;
    private Date dob;
    private String college_name;
    private String passout_year;
    private float cgpa;
    private int experiance;
    private String address;
    private String resume_link;
    private String email;
    private String job_role;
    private String emp_id;

    public profile_details(){}

    public profile_details(int p_id, String phone, String gender, Date dob, String college_name, String passout_year,
            float cgpa, int experiance, String address, String resume_link, String email, String job_role,
            String emp_id) {
        this.p_id = p_id;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.college_name = college_name;
        this.passout_year = passout_year;
        this.cgpa = cgpa;
        this.experiance = experiance;
        this.address = address;
        this.resume_link = resume_link;
        this.email = email;
        this.job_role = job_role;
        this.emp_id = emp_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getPassout_year() {
        return passout_year;
    }

    public void setPassout_year(String passout_year) {
        this.passout_year = passout_year;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResume_link() {
        return resume_link;
    }

    public void setResume_link(String resume_link) {
        this.resume_link = resume_link;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob_role() {
        return job_role;
    }

    public void setJob_role(String job_role) {
        this.job_role = job_role;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    

    

    
}
