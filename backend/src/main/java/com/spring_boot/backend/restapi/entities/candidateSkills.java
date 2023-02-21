package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="candidate_skills")
public class candidateSkills {

    
    @Id
    private candidateSkillsId cSId;
 
    public candidateSkills(){}

    public candidateSkills(candidateSkillsId cSId) {
        this.cSId = cSId;
    }

    public candidateSkillsId getcSId() {
        return cSId;
    }

    public void setcSId(candidateSkillsId cSId) {
        this.cSId = cSId;
    }

}
