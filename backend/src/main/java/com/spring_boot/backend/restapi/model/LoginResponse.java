package com.spring_boot.backend.restapi.model;

public class LoginResponse {

    private String token;
    private String message;

    public LoginResponse(){}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

   
}
