package com.spring_boot.backend.restapi.repositories;


import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.*;

public interface candidateProfileRepository extends CrudRepository <candidateProfile,String>{
   @Query("SELECT new com.spring_boot.backend.restapi.entities.candidateProfile(r.pId,r.address,r.experience,r.phone_no,r.cSignup) FROM candidateProfile r WHERE r.cSignup.cid=:id")
   public candidateProfile findByCId(@Param("id") String cid);

   candidateProfile findBycSignupCid(String cid);

   @Query("SELECT c FROM candidateProfile c WHERE c.cSignup.cid IN (:cids) ")
   List<candidateProfile> findByCIds(List<String> cids);

   public candidateProfile findBypId(String pId);

   @Query("SELECT c.cSignup.email FROM candidateProfile c INNER JOIN candidateSkills s WHERE s.cSId.skills IN (:skills)")
   List<String> findBycandidateSkills(List<String> skills);

 
}
