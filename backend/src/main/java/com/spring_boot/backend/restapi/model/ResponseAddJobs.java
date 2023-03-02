package com.spring_boot.backend.restapi.model;

import java.util.List;

public class ResponseAddJobs {
    private String error;
    private List<String> emails;
    private String jobrole;

    public ResponseAddJobs() {}

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getJobrole() {
        return jobrole;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    

    
}
