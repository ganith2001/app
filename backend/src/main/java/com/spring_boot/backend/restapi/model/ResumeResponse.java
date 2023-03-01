package com.spring_boot.backend.restapi.model;

public class ResumeResponse {

    private String resumeid;
    private String file_name;

    public ResumeResponse(){}

    public String getResumeid() {
        return resumeid;
    }
    public void setResumeid(String resumeid) {
        this.resumeid = resumeid;
    }
    public String getFile_name() {
        return file_name;
    }
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
  
}
