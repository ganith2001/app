package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring_boot.backend.restapi.entities.jobs;


public interface jobsRepository extends CrudRepository<jobs,Integer> {

    
}