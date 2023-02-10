package com.spring_boot.backend.restapi.entities;




import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;





@Entity
public class applied_jobs {

   @EmbeddedId
   private email_job_PK id;
 
   private String status;
   //private String job_role;

   public applied_jobs(){};

public applied_jobs(email_job_PK id, String status) {
    this.id = id;
    this.status = status;
}

public email_job_PK getId() {
    return id;
}

public void setId(email_job_PK id) {
    this.id = id;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
    


    
    
}
