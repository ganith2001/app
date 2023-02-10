package com.spring_boot.backend.restapi.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring_boot.backend.restapi.entities.profile_details;

public interface profileAppliedjobsJobsRepository extends CrudRepository<profile_details,Integer>{

    @Query("SELECT new com.spring_boot.backend.restapi.entities.profile_details(p.p_id,p.phone,p.gender,p.dob,p.college_name,p.passout_year,p.cgpa,p.experiance,p.address,p.resume_link,p.email,j.job_role,j.emp_id) FROM profile_details p INNER JOIN apply a ON p.email = a.email INNER JOIN jobs j ON j.job_id=a.job_id where j.emp_id=:emp and j.job_role = :job")
    public List<profile_details> getProfileByEmpid(@Param("emp") String emp_id,@Param("job") String job_role);
    
}
