package com.spring_boot.backend.restapi.repositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import org.springframework.data.repository.query.Param;

import com.spring_boot.backend.restapi.entities.profile;

import org.springframework.data.jpa.repository.Query;

public interface profileRepository extends CrudRepository<profile,Integer> {

    @Query("FROM profile p WHERE p.email=:e")
    public List<profile> findOne(@Param("e") String email);
}
