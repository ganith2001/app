package com.spring_boot.backend.restapi.repositories;


import org.springframework.data.repository.CrudRepository;


import com.spring_boot.backend.restapi.entities.candidateSignup;


public interface candidateSignupRepository extends CrudRepository<candidateSignup,String> {



    candidateSignup findByName(String username);

    boolean existsByName(String username);

    boolean existsByEmail(String email);

    candidateSignup findByEmail(String email);

    candidateSignup findByCid(String cid);
    
    boolean existsByCid(String cid);

   
    


    
}
