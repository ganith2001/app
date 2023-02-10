package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class emp_login {
    @Id
    private String emp_id;
    private String username;
    private String email;
    private String password;

    public emp_login(){

    }

    public emp_login(String emp_id,String username,String email,String password){
        this.emp_id=emp_id;
        this.username=username;
        this.email=email;
        this.password=password;     
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    
}

