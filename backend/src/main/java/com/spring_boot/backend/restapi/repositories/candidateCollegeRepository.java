package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.candidateCollegeDetail;
import org.springframework.data.jpa.repository.Query;

public interface candidateCollegeRepository extends CrudRepository <candidateCollegeDetail,String> {

  @Query("SELECT c FROM candidateCollegeDetail c WHERE c.clg_id=:cid")
  public candidateCollegeDetail findByClgid(String cid);
}
