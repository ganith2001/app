package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.RequiredSkills;

public interface RequiredSkillsRepository extends CrudRepository<RequiredSkills,String> {
    
}
