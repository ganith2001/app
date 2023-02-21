package com.spring_boot.backend.restapi.entities;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="candidate_signup",uniqueConstraints=@UniqueConstraint(name="uk_email",columnNames=("email")))
public class candidateSignup {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
    private String cid;
    private String name;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    public candidateSignup(){}

    public candidateSignup(String cid){
        this.cid = cid;
    }

    public candidateSignup(String cid, String email,String name, String password) {
        this.cid = cid;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    

    public candidateSignup(String cid, String name, String email) {
        this.cid = cid;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCId() {
        return cid;
    }

    public void setCId(String cid) {
        this.cid = cid;
    }



}
