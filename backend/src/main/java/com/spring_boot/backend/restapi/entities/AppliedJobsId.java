package com.spring_boot.backend.restapi.entities;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;




@Embeddable
public class AppliedJobsId {

    @ManyToOne
    @JoinColumn(name="cid")
    private candidateSignup candidate;

    @ManyToOne
    @JoinColumn(name="job_id")
    private Job job;

    public AppliedJobsId(){}

    public AppliedJobsId(com.spring_boot.backend.restapi.entities.candidateSignup candidateSignup) {
        this.candidate = candidateSignup;
      
    }

    public AppliedJobsId(com.spring_boot.backend.restapi.entities.candidateSignup candidateSignup, Job job) {
        this.candidate = candidateSignup;
        this.job = job;
    }

    public candidateSignup getCandidateSignup() {
        return candidate;
    }

    public void setCandidateSignup(candidateSignup candidateSignup) {
        this.candidate = candidateSignup;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
    
}
