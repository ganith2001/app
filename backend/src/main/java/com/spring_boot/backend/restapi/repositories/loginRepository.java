package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring_boot.backend.restapi.entities.login;

public interface loginRepository extends CrudRepository<login,String> {

    @Query("SELECT new com.spring_boot.backend.restapi.entities.login(l.email,l.username,l.password) FROM login l WHERE l.email=:e AND l.password=:p")
    public login getToken(@Param("e") String email,@Param("p") String password);

    
}
