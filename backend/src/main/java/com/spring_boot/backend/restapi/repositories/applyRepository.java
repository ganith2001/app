package com.spring_boot.backend.restapi.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring_boot.backend.restapi.entities.apply;

public interface applyRepository extends CrudRepository<apply,String>{

    @Query("SELECT new com.spring_boot.backend.restapi.entities.apply(a.email,a.job_id,a.status,j.job_role,j.location,j.apply_by,j.ctc,j.cgpa,j.experiance,j.descrption) FROM apply a join jobs j where a.job_id=j.job_id and a.email = :e")
    public List<apply> getAppliedjobsByUser(@Param("e") String email);
    
}
