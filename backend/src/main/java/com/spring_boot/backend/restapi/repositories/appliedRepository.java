package com.spring_boot.backend.restapi.repositories;




import org.springframework.data.repository.CrudRepository;

import com.spring_boot.backend.restapi.entities.applied_jobs;

public interface appliedRepository extends CrudRepository<applied_jobs,String> {
 
}
