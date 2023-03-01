package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.AppliedJob;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppliedJobRepository extends CrudRepository<AppliedJob,String> {
   
   public List<AppliedJob> findByappliedJobsIdCandidateCid(String cid);

   @Query("SELECT (a.appliedJobsId.candidate.cid) FROM AppliedJob a WHERE a.appliedJobsId.job.job_id=:job_id")
   public List<String> findByappliedJobsIdJobJobid(String job_id);

   @Query("SELECT (a.appliedJobsId.candidate.cid) FROM AppliedJob a WHERE a.appliedJobsId.job.job_id=:job_id and a.status='Shortlisted'")
   public List<String> findByappliedjobsIdJobAndShortLists(String job_id);

   @Transactional
   @Modifying
   @Query("DELETE FROM AppliedJob a WHERE a.appliedJobsId.job.job_id=:jobid")
   public void deleteByappliedJobsIdJobJob_id(String jobid);
}
