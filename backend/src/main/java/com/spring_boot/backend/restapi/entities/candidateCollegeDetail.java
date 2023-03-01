package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.GeneratedValue;


@Entity
@Table(name="college_details")
public class candidateCollegeDetail {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
    private String clg_id;
    private String college_name;
    private String degree;
    private String course;
    private float cgpa;
    private int passout_year;

    @ManyToOne
    @JoinColumn(name="pId")
    private candidateProfile candidateProfile;
    
    public candidateCollegeDetail(){}

    public candidateCollegeDetail( String college_name, String degree, String course, float cgpa,
    int passout_year) {

        this.college_name = college_name;
        this.degree = degree;
        this.course = course;
        this.cgpa = cgpa;
        this.passout_year = passout_year;

    }


    public candidateCollegeDetail( String college_name, String degree, String course, float cgpa,
            int passout_year,candidateProfile candidateProfile) {
     
        this.college_name = college_name;
        this.degree = degree;
        this.course = course;
        this.cgpa = cgpa;
        this.passout_year = passout_year;
        this.candidateProfile=candidateProfile;
    }


    

    public String getClg_id() {
        return clg_id;
    }

    public void setClg_id(String clg_id) {
        this.clg_id = clg_id;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public int getPassout_year() {
        return passout_year;
    }

    public void setPassout_year(int passout_year) {
        this.passout_year = passout_year;
    }

    

}
