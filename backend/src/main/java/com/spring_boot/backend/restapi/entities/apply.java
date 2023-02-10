package com.spring_boot.backend.restapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="applied_jobs")
public class apply {

    @Id
    private String email;
    private int job_id;
    private String status;
    private String job_role;
    private String location;
    private Date apply_by;
     private int ctc;
     private float cgpa;
     private int experiance;
     private String descrption;
    

    public apply(){};
    
 

    public apply(String email, int job_id, String status, String job_role , String location, Date apply_by, int ctc 
           , float cgpa  , int experiance, String descrption) {
        this.email = email;
        this.job_id = job_id;
        this.status = status;
        this.job_role = job_role;
        this.location = location;
        this.apply_by = apply_by;
         this.ctc = ctc;
        this.cgpa = cgpa;
          this.experiance = experiance;
          this.descrption = descrption;
     }

   




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJob_role() {
        return job_role;
    }

    public void setJob_role(String job_role) {
        this.job_role = job_role;
    }

    
    public String getLocation() {
        return location;
    }



    public void setLocation(String location) {
        this.location = location;
    }



    public Date getApply_by() {
        return apply_by;
    }



    public void setApply_by(Date apply_by) {
        this.apply_by = apply_by;
    }



     public int getCtc() {
         return ctc;
     }



     public void setCtc(int ctc) {
        this.ctc = ctc;
     }



      public float getCgpa() {
          return cgpa;
      }



     public void setCgpa(int cgpa) {
          this.cgpa = cgpa;
      }



      public int getExperiance() {
          return experiance;
      }



      public void setExperiance(int experiance) {
          this.experiance = experiance;
      }



     public String getDescrption() {
         return descrption;
     }



    public void setDescrption(String descrption) {
          this.descrption = descrption;
      }

    
}
