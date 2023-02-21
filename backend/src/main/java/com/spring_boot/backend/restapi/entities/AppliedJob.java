package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AppliedJob {

    @Id
    private AppliedJobsId appliedJobsId;

    private String status="Applied";

    public AppliedJob(){}

    public AppliedJob(AppliedJobsId appliedJobsId) {
        this.appliedJobsId = appliedJobsId;
        
    }

    public AppliedJob(AppliedJobsId appliedJobsId, String status) {
        this.appliedJobsId = appliedJobsId;
        this.status = status;
    }

    public AppliedJobsId getAppliedJobsId() {
        return appliedJobsId;
    }

    public void setAppliedJobsId(AppliedJobsId appliedJobsId) {
        this.appliedJobsId = appliedJobsId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    


    
}
