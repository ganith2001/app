package com.spring_boot.backend.restapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring_boot.backend.restapi.model.ResponseData;
import com.spring_boot.backend.restapi.services.dataservice;

import com.spring_boot.backend.restapi.entities.*;
import com.spring_boot.backend.restapi.model.*;
import com.spring_boot.backend.restapi.Authentication.config.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring_boot.backend.restapi.services.*;



import java.time.Instant;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class dataController {

    @Autowired
    private dataservice dservice;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
	private EmailSenderService senderService;

   
    private String OTP;
    private Long timestamp;


    @PostMapping("/candidateSignup")
    public UserCreateResponse add(@RequestBody candidateSignupRequest c){
        
        UserCreateResponse userCreateResponse = new UserCreateResponse();
    
        if(OTP != null && timestamp != null){
     
            String usercreated=dservice.addCandidate(c,this.OTP,this.timestamp);
            System.out.println(usercreated);
            

        }
        else{
            userCreateResponse.setRes("Cannot create user !!!");
        }
        return userCreateResponse;
    }

    @PostMapping("/otpGenerate")
    public void otpGenerate(@RequestBody String email){

        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();
        this.timestamp=timeStampSeconds;
        
        Random random = new Random();
        int max=99999;
        int min=10000;
        int otp =random.nextInt(max+1-min)+min;
        
        String otp2=Integer.toString(otp);
        this.OTP=otp2;
  
       
        System.out.println(this.OTP);
        senderService.sendSimpleEmail(email,
				"OTP for verification",
				"This otp is "+otp2);
        
        

    }

    @PostMapping("/candidateLogin")
    public LoginResponse candidatelogin(@RequestBody AuthRequest authRequest){

        candidateSignup res = dservice.candidateLogin(authRequest);
        LoginResponse response = new LoginResponse();
        if(res!=null){ 
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(res.getName(), authRequest.getPassword()));
            
            if (authentication.isAuthenticated()) {
                String role=(String) authentication.getAuthorities().toArray()[0].toString();
                 String token= authenticationService.generateToken(res.getCId(),role); 
                 response.setToken(token);
                 response.setMessage("Successfull");
                 return response;
    
            }
            else{
            
               throw new UsernameNotFoundException("invalid user request !");
    
            }
        }
        response.setMessage("Login Failed");
        return response;
    }

    @PostMapping("/recruiterSignup")
    public String add(@RequestBody recruiterSignup r){
        return dservice.addRecruiter(r);
    }

    @PostMapping("/recruiterLogin")
    public LoginResponse recruiterLogin(@RequestBody AuthRequest authRequest ){
        recruiterSignup res=dservice.recruiterLogin(authRequest);
        LoginResponse response = new LoginResponse();
        if(res!=null){ 
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(res.getName(), authRequest.getPassword()));
        
            if (authentication.isAuthenticated()) {
                String role=(String) authentication.getAuthorities().toArray()[0].toString();
                String token= authenticationService.generateToken(res.getEmp_id(),role);
                 response.setToken(token);
                 response.setMessage("Successfull");
                 return response;
            }
            else{
        
           throw new UsernameNotFoundException("invalid user request !");

            }
        }
        response.setMessage("Login Failed");
        return response;
    }

    @PostMapping("/createProfile")
    public void createProfile(@RequestBody createProfileRequest cPRequest){
        dservice.createProfile(cPRequest);
    }


    @PostMapping("/addJobs")
    public ResponseMessage addJob(@RequestBody JobsRequest j){
        dservice.addJobs(j);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setError("user created");
        return responseMessage;
    }

    @PostMapping("/applyJobs")
    public void apply(@RequestBody ApplyJobsRequest appliedJob){
        dservice.applyjobs(appliedJob);
    }


    @PutMapping("/updateStatus/{cid}/{jobid}")
    public void updateStatus(@PathVariable String cid,@PathVariable String jobid,@RequestBody String status){
        dservice.updateStatus(cid, jobid,status);
    }

    @GetMapping("/getProfile/{cid}")
    public candidateProfile getProfile(@PathVariable String cid){
        return dservice.getProfile(cid);
    }

    @GetMapping("/getAllJobs")
    public List<Job> getAllJobs(){
        return dservice.getAllJobs();
    }

    @GetMapping("/getJobsByEmpId/{empid}")
    public List<Job> getJobsByEmpid(@PathVariable String empid){
        return dservice.getJobsByEmpid(empid);
    }

    @GetMapping("/getAppliedJobsByCid/{cid}")
    public List<AppliedJob> getAppliedJobsByCid(@PathVariable String cid){
        return dservice.getAppliedJobsByCid(cid);

    }

    @GetMapping("/getAppliedJobsIdByCid/{cid}")
    public List<String> getAppliedJobsIdByCid(@PathVariable String cid){
        List<String> jobsIds=new ArrayList<>();
   
        for(var jobs:dservice.getAppliedJobsByCid(cid)){
            jobsIds.add(jobs.getAppliedJobsId().getJob().getJob_id());
        }
    
        return jobsIds;
    }



    @GetMapping("/getCandidatesByJobid/{jobid}")
    public List<candidateProfile> getCandidatesByJobid(@PathVariable String jobid){
         return dservice.getCandidatesByJobid(jobid);
    }


     @DeleteMapping("/deleteJob/{id}")
    public void deleteJobById(@PathVariable String id){
        dservice.deleteJobById(id);
    }


    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("cid") String cid) throws Exception {
        resume attachment = null;
        String downloadURl = "";
        attachment = dservice.saveAttachment(file,cid);
         downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();

        return new ResponseData(attachment.getFileName(),
                downloadURl,
                file.getContentType(),
                file.getSize()); 
    }

    @GetMapping("/download/{fileId}")
   @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        resume attachment = null;
        attachment = dservice.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

   /*  @PostMapping("/authentication")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        
        if (authentication.isAuthenticated()) {
            String role=(String) authentication.getAuthorities().toArray()[0].toString();
             return authenticationService.generateToken(authRequest.getName(),role); 
        }
        else{
        
           throw new UsernameNotFoundException("invalid user request !");
        }

     
          
    } 
*/
 
}
