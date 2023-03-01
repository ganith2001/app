package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring_boot.backend.restapi.entities.resume;


public interface resumeRepository extends CrudRepository<resume,String> {

    @Query("SELECT r FROM resume r WHERE r.cProfile.pId=:pid")
    public resume findBycProfilePId(String pid);
}
