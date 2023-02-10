package com.spring_boot.backend.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_boot.backend.restapi.entities.applied_jobs;
import com.spring_boot.backend.restapi.entities.apply;
import com.spring_boot.backend.restapi.entities.email_job_PK;
import com.spring_boot.backend.restapi.entities.jobs;
import com.spring_boot.backend.restapi.entities.login;
import com.spring_boot.backend.restapi.entities.profile;
import com.spring_boot.backend.restapi.entities.profile_details;
import com.spring_boot.backend.restapi.entities.resume;
import com.spring_boot.backend.restapi.repositories.appliedRepository;
import com.spring_boot.backend.restapi.repositories.applyRepository;
import com.spring_boot.backend.restapi.repositories.jobsRepository;
import com.spring_boot.backend.restapi.repositories.loginRepository;
import com.spring_boot.backend.restapi.repositories.profileAppliedjobsJobsRepository;
import com.spring_boot.backend.restapi.repositories.profileRepository;
import com.spring_boot.backend.restapi.repositories.resumeRepository;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

@Service
public class dataservice {

    @Autowired
    private loginRepository lRepository ;

    @Autowired
    private jobsRepository jRepository;

    @Autowired
    private appliedRepository aRepository;

    @Autowired
    private applyRepository appRepository;
    
    @Autowired
    private profileAppliedjobsJobsRepository pajRepository;

    @Autowired
    private profileRepository pRepository;

    //@Autowired
    //private JwtUtils jwt;

    @Autowired
    private resumeRepository rRepository;


    public void addlogin(login inp){
        this.lRepository.save(inp);
    }

    public void addjobs(jobs j){
        this.jRepository.save(j);

    }

    public List<jobs> getjobs(){
        List<jobs> list=(List<jobs>) this.jRepository.findAll();
        return list;
    }

    public List<apply> getappliedjobs(String email){
        List<apply> list=(List<apply>) this.appRepository.getAppliedjobsByUser(email);
        return list;
    }
    

    public void applyjobs(applied_jobs a){
        this.aRepository.save(a);
    }
    
    public List<profile_details> getprofilebyempid(String emp_id,String job_role){
        List<profile_details> list=(List<profile_details>) this.pajRepository.getProfileByEmpid(emp_id, job_role);
        return list;
    }

    public void updateStatus(String email,int job_id,String status){
        email_job_PK pk=new email_job_PK(email,job_id);
        applied_jobs a=new applied_jobs(pk,status);
        this.aRepository.save(a);
    }

    public void addProfile(profile p){
        this.pRepository.save(p);
        

    }

    public List<profile> getProfileByUser(String email){
        List<profile> list=(List<profile>) this.pRepository.findOne(email);
        return list;

    }

    public void deleteJobById(int id){
       
        this.jRepository.deleteById(id);
    }


    public String login(login loginRequest) {

        

        // validation

        // verify user exist with given email and password
        login user = this.lRepository.getToken(loginRequest.getEmail(),loginRequest.getPassword());

        // response
        if(user == null){
            return "Login failed";
        }

        //String token = this.jwt.generateJwt(user);
        return "Login Successfull";
        // generate jwt
        

        
    }


    public resume saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
             if(fileName.contains("..")) {
                 throw  new Exception("Filename contains invalid path sequence "
                 + fileName);
             }
             
             resume attachment
                     = new resume(fileName,
                     file.getContentType(),
                     file.getBytes(),2);

             return this.rRepository.save(attachment); 
 
        } catch (Exception e) {
             throw new Exception("Could not save File: " + fileName);
        }
     }
 
     
     public resume getAttachment(String fileId) throws Exception {
         return this.rRepository
                 .findById(fileId)
                 .orElseThrow(
                         () -> new Exception("File not found with Id: " + fileId));
     } 
}
