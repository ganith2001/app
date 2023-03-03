package com.spring_boot.backend.restapi.model;

public class ForgetPasswordRequest {

    private String password;
    private String otp;

    public ForgetPasswordRequest(){}

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
