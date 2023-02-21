package com.spring_boot.backend.restapi.model;

public class ApplyJobsRequest {

    private String cid;
    private String job_id;

    public ApplyJobsRequest() {}

    public ApplyJobsRequest(String cid, String job_id) {
        this.cid = cid;
        this.job_id = job_id;
    }

    public String getcid() {
        return cid;
    }

    public void setcid(String cid) {
        this.cid = cid;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    
    
}
