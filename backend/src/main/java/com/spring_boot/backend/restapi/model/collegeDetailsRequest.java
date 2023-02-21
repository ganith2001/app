package com.spring_boot.backend.restapi.model;

public class collegeDetailsRequest {

    private String college_name;
    private String degree;
    private String course;
    private float cgpa;
    private int passout_year;

    public collegeDetailsRequest() {}
    
    public collegeDetailsRequest(String college_name, String degree, String course, float cgpa, int passout_year) {
        this.college_name = college_name;
        this.degree = degree;
        this.course = course;
        this.cgpa = cgpa;
        this.passout_year = passout_year;
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
