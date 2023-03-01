package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.RequiredSkills;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

public interface RequiredSkillsRepository extends CrudRepository<RequiredSkills,String> {

   @Transactional
   @Modifying
   @Query("DELETE FROM RequiredSkills r WHERE r.requiredSkillsId.job.job_id=:jobid")
   public void deleteByRequiredSkillsJob_id(String jobid);
   
}
