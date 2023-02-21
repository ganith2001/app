package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.*;
import java.util.List;

public interface candidateSkillsRepository extends CrudRepository<candidateSkills,String>{
   
   public List<candidateSkills> findAll();
}
