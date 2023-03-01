package com.spring_boot.backend.restapi.model;

public class PassswordRequest {

    private String oldpassword;
    private String newPassword;

    public PassswordRequest(String oldpassword, String newPassword) {
        this.oldpassword = oldpassword;
        this.newPassword = newPassword;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    
   
}
