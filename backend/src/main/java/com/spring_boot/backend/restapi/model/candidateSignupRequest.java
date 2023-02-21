package com.spring_boot.backend.restapi.model;

public class candidateSignupRequest {

    private String name;
    private String email;
    private String password;
    private String otp;

    public candidateSignupRequest(){}

    public candidateSignupRequest(String name, String email, String password, String otp) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.otp = otp;
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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    


    
    
}
