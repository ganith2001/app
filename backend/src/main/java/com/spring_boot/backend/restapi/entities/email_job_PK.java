package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class email_job_PK implements Serializable{

    private String email;
    private int job_id;

    public email_job_PK(){}
    
    public email_job_PK(String email, int job_id) {
        this.email = email;
        this.job_id = job_id;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public int getJob_id() {
        return job_id;
    }


    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    
    
    
}
