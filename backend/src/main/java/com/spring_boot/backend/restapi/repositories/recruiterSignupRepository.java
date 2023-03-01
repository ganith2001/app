package com.spring_boot.backend.restapi.repositories;


import org.springframework.data.repository.CrudRepository;


import com.spring_boot.backend.restapi.entities.recruiterSignup;

public interface recruiterSignupRepository extends CrudRepository<recruiterSignup,String>{

    
    recruiterSignup findByName(String name);

    recruiterSignup findByEmail(String email);

    recruiterSignup findByEmpid(String empid);
    
}
