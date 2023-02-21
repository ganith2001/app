package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class RequiredSkillsId {
    
    @ManyToOne
    @JoinColumn(name="job_id")
    private Job job;

    private String skills;
 
    public RequiredSkillsId(){}

    public RequiredSkillsId(Job job, String skills) {
        this.job = job;
        this.skills = skills;
    }

  

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    

}
