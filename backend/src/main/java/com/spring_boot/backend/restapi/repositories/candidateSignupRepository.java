package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring_boot.backend.restapi.entities.candidateSignup;


public interface candidateSignupRepository extends CrudRepository<candidateSignup,String> {

   // @Query("SELECT new com.spring_boot.backend.restapi.entities.candidateSignup(l.cid,l.email,l.name,l.password) FROM candidateSignup l WHERE l.email=:e AND l.password=:p")
   // public candidateSignup getToken(@Param("e") String email,@Param("p") String password);

    candidateSignup findByName(String username);

    boolean existsByName(String username);

    candidateSignup findByEmail(String email);
    


    
}
