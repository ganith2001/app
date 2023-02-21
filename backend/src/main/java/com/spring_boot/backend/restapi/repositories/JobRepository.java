package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.Job;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends CrudRepository<Job,String> {

   //@Query("SELECT new com.spring_boot.backend.restapi.entities.Job(j.job_role,j.ctc,j.location,j.apply_by,j.cgpa,j.experiance,j.description,j.requiredSkills)  FROM Job j")
    public List<Job> findAll();

    //@Query("SELECT new com.spring_boot.backend.restapi.entities.Job(j.job_role,j.ctc,j.location,j.apply_by,j.cgpa,j.experiance,j.description)  FROM Job j WHERE j.recruiterSignup.empid=:empid")
    public List<Job> findByrecruiterSignupEmpid(String empid);

    //@Query("DELETE FROM Job j WHERE j.recruiterSignup.empid=:empid")
    public void deleteByrecruiterSignupEmpid(String empid);
    
}
