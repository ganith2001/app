package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class RequiredSkills {

    @Id
    private RequiredSkillsId requiredSkillsId;
 
    public RequiredSkills(){}

    public RequiredSkills(RequiredSkillsId requiredSkillsId) {
        this.requiredSkillsId = requiredSkillsId;
    }

    public RequiredSkillsId getRequiredSkillsId() {
        return requiredSkillsId;
    }

    public void setRequiredSkillsId(RequiredSkillsId requiredSkillsId) {
        this.requiredSkillsId = requiredSkillsId;
    }
    
}
