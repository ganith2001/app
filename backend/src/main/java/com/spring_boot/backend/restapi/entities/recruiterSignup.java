package com.spring_boot.backend.restapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name="recruiter_signup",uniqueConstraints=@UniqueConstraint(name="uk2_email",columnNames=("email")))
public class recruiterSignup {
    @Id
    private String empid;
    private String name;
    private String email;
    private String roles;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

 
    public recruiterSignup(){}
 

    public recruiterSignup(String empid,String email,String name,String password){
        this.empid=empid;
        this.name=name;
        this.email=email;
        this.password=password;     
    }

    

    public String getRoles() {
        return roles;
    }


    public void setRoles(String roles) {
        this.roles = roles;
    }


    public String getEmp_id() {
        return empid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmp_id(String empid) {
        this.empid = empid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}

