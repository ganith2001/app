package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.*;
import java.util.*;

@Embeddable
public class candidateSkillsId implements Serializable {

    @ManyToOne
    @JoinColumn(name="pId")
    private candidateProfile candidateProfile;

    private String skills;

    public candidateSkillsId(){}

    
    public candidateSkillsId(candidateProfile candidateProfile, String skills) {
        this.candidateProfile=candidateProfile;
        this.skills = skills;
    }

    

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }


   

    
}
