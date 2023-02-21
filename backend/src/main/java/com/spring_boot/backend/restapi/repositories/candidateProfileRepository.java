package com.spring_boot.backend.restapi.repositories;


import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.spring_boot.backend.restapi.model.*;

import java.util.*;

public interface candidateProfileRepository extends CrudRepository <candidateProfile,String>{
   @Query("SELECT new com.spring_boot.backend.restapi.entities.candidateProfile(r.pId,r.address,r.experience,r.phone_no,r.cSignup) FROM candidateProfile r WHERE r.cSignup.cid=:id")
   public candidateProfile findByCId(@Param("id") String cid);

 //  @Query("SELECT new com.spring_boot.backend.restapi.entities.CandidateProfileResponse(b.address,b.experience,b.phone_no,b.cid) FROM candidateSkills a JOIN candidateProfile b ON a.cSId.cProfile.pId=b.pId JOIN candidateCollegeDetail d ON d.cProfile.pId=b.pId JOIN candidateSignup c ON c.cid=b.cSignup.cid WHERE b.cSignup.cid=:id")
  // public CandidateProfileResponse getProfileDetails(@Param("id") String cid);

   candidateProfile findBycSignupCid(String cid);

   @Query("SELECT c FROM candidateProfile c WHERE c.cSignup.cid IN (:cids) ")
   List<candidateProfile> findByCIds(List<String> cids);

 
}
