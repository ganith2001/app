package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.Job;

import java.util.List;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface JobRepository extends CrudRepository<Job,String> {

   
    public List<Job> findAll();

    
    public List<Job> findByrecruiterSignupEmpid(String empid);

 
    public void deleteByrecruiterSignupEmpid(String empid);

    @Transactional
   @Modifying
   @Query("DELETE FROM Job j WHERE j.job_id=:jobid")
   public void deleteJobsByJob_id(String jobid);
    
}
