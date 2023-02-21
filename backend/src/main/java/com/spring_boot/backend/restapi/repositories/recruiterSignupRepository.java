package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring_boot.backend.restapi.entities.recruiterSignup;

public interface recruiterSignupRepository extends CrudRepository<recruiterSignup,String>{

   // @Query("SELECT new com.spring_boot.backend.restapi.entities.recruiterSignup(r.empid,r.email,r.name,r.password) FROM recruiterSignup r WHERE r.name=:n AND r.password=:p")
   // public recruiterSignup getTok(@Param("n") String name,@Param("p") String password);
    
    recruiterSignup findByName(String name);

    recruiterSignup findByEmail(String email);
}
