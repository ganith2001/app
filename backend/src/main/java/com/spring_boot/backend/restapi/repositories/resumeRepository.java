package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring_boot.backend.restapi.entities.resume;


public interface resumeRepository extends CrudRepository<resume,String> {

    
}
