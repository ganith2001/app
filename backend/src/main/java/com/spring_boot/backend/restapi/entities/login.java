package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class login {

    @Id
    private String email;
    
    private String username;
    private String password;
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public login(){

    }

    public login(String email,String username,String password){
        this.email=email;
        this.username=username;
        this.password=password;
    }
}
