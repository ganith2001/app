package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.candidateCollegeDetail;

public interface candidateCollegeRepository extends CrudRepository <candidateCollegeDetail,String> {
    
}
