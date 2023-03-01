package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.*;
import java.util.List;

public interface candidateSkillsRepository extends CrudRepository<candidateSkills,String>{
   
   public List<candidateSkills> findAll();

   @Transactional
   @Modifying
   @Query("DELETE FROM candidateSkills c WHERE c.cSId.candidateProfile.pId=:pid AND c.cSId.skills=:skill")
   void deleteskill(String pid,String skill);
}
