package com.spring_boot.backend.restapi.repositories;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.spring_boot.backend.restapi.entities.AppliedJob;

import java.util.List;

public interface AppliedJobRepository extends CrudRepository<AppliedJob,String> {
   // @Query("SELECT new com.spring_boot.backend.restapi.entities.AppliedJob() FROM AppliedJob a WHERE a.appliedJobsId.candidate.c_id=:c_id AND a.appliedJobsId.job.job_id=:job_id ")

   //@Query("SELECT new com.spring_boot.backend.restapi.entities.AppliedJob(,a.status) FROM AppliedJob a JOIN Job j ON a.job_id=j.appliedJobsId.job.job_id  WHERE a.appliedJobsId.candidate.cid=:cid")
   public List<AppliedJob> findByappliedJobsIdCandidateCid(String cid);

   @Query("SELECT (a.appliedJobsId.candidate.cid) FROM AppliedJob a WHERE a.appliedJobsId.job.job_id=:jobid")
   public List<String> findByappliedJobsIdJobJobid(String jobid);
}
//new com.spring_boot.backend.restapi.entities.AppliedJobsId(new com.spring_boot.backend.restapi.entities.candidateSignup(a.appliedJobsId.candidate.cid)),