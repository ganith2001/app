package com.spring_boot.backend.restapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring_boot.backend.restapi.services.*;
import com.spring_boot.backend.restapi.entities.*;
import com.spring_boot.backend.restapi.model.*;
import com.spring_boot.backend.restapi.Authentication.config.AuthenticationService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.time.Instant;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@EnableTransactionManagement 
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
            userCreateResponse.setRes(usercreated);

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
            
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(res.getCId(), authRequest.getPassword()));
          
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
        //System.out.println(response.);
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
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(res.getEmp_id(), authRequest.getPassword()));
       
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
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public void createProfile(@RequestBody createProfileRequest cPRequest){
        dservice.createProfile(cPRequest);
    }


    @PostMapping("/addJobs")
    @PreAuthorize("hasAuthority('RECRUITER')")
    public ResponseAddJobs addJob(@RequestBody JobsRequest j){
        List<String> emails=dservice.addJobs(j);
        ResponseAddJobs responseMessage = new ResponseAddJobs();
        responseMessage.setError("user created");
        responseMessage.setEmails(emails);
        responseMessage.setJobrole(j.getJob_role());
        return responseMessage;
    }

    @PostMapping("/applyJobs")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public void apply(@RequestBody ApplyJobsRequest appliedJob){
        dservice.applyjobs(appliedJob);
    }


    @PutMapping("/updateStatus/{cid}/{jobid}")
    @PreAuthorize("hasAuthority('RECRUITER')")
    public void updateStatus(@PathVariable String cid,@PathVariable String jobid,@RequestBody String status){
         dservice.updateStatus(cid, jobid,status);
    }

    @GetMapping("/getProfile/{cid}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public candidateProfile getProfile(@PathVariable String cid){
        return dservice.getProfile(cid);
    }

    @GetMapping("/getAllJobs")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public List<Job> getAllJobs(){
        return dservice.getAllJobs();
    }

    @GetMapping("/getJobsByEmpId/{empid}")
    @PreAuthorize("hasAuthority('RECRUITER')")
    public List<Job> getJobsByEmpid(@PathVariable String empid){
        return dservice.getJobsByEmpId(empid);
    }

    @GetMapping("/getAppliedJobsByCid/{cid}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public List<AppliedJob> getAppliedJobsByCid(@PathVariable String cid){
        return dservice.getAppliedJobsByCid(cid);

    }

    @GetMapping("/getAppliedJobsIdByCid/{cid}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public List<String> getAppliedJobsIdByCid(@PathVariable String cid){
        List<String> jobsIds=new ArrayList<>();
   
        for(var jobs:dservice.getAppliedJobsByCid(cid)){
            jobsIds.add(jobs.getAppliedJobsId().getJob().getJob_id());
        }
    
        return jobsIds;
    }

    @GetMapping("/getAppliedjobsIdJobAndShortLists/{jobid}")
    @PreAuthorize("hasAuthority('RECRUITER')")
    public List<String> getAppliedjobsIdJobAndShort(@PathVariable String jobid){
       
         return dservice.getAppliedjobsIdJobAndShortLists(jobid);
        
      
    }



    @GetMapping("/getCandidatesByJobid/{jobid}")
    @PreAuthorize("hasAuthority('RECRUITER')")
    public List<candidateProfile> getCandidatesByJobid(@PathVariable String jobid){
         return dservice.getCandidatesByJobid(jobid);
    }


     @DeleteMapping("/deleteJobsByJobId/{id}")
     @PreAuthorize("hasAuthority('RECRUITER')")
    public void deleteJobById(@PathVariable String id){
        System.out.println("kkk");
        dservice.deleteJobById(id);
    }


    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public ResponseData uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("cid") String cid) throws Exception {
        resume attachment = null;
        String downloadURl = "";
        attachment = dservice.saveAttachment(file,cid);
         downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getResumeid())
                .toUriString();

        return new ResponseData(attachment.getFile_name(),
                downloadURl,
                file.getContentType(),
                file.getSize()); 
    }

    @DeleteMapping("/deleteResume/{resumeid}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public void deleteResume(@PathVariable String resumeid){
        dservice.deleteResume(resumeid);
    }

    @PutMapping("/updateProfile/{p_id}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public void updateProfile(@PathVariable String p_id,@RequestBody UpdateProfileRequest updateProfileRequest){
   
         dservice.updateProfile(p_id,updateProfileRequest);
    }

    @PutMapping("/updateSkills/{p_id}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public void updateSkills(@PathVariable String p_id,@RequestBody String skill){
         dservice.updateSkills(p_id,skill);
    }

    @DeleteMapping("/deleteSkills/{pid}/{skill}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public void deleteSkills(@PathVariable String pid,@PathVariable String skill){
        dservice.deleteSkills(pid,skill);
    }

    @PutMapping("/updateCollege/{cid}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public void updateCollege(@PathVariable String cid,@RequestBody collegeDetailsRequest collegeDetailsRequest){
        dservice.updateCollege(cid,collegeDetailsRequest);

    }

    @PutMapping("/changePassword/{cid}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public ResponseMessage changePassword(@PathVariable String cid,@RequestBody PassswordRequest password){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cid, password.getOldpassword()));
        ResponseMessage responseMessage = new ResponseMessage();
        if(authentication.isAuthenticated()){
            responseMessage.setError(dservice.changePassword(cid, password));
            return responseMessage;
            
        }
            responseMessage.setError("Wrong Password");
            return responseMessage;
          
    }

    @GetMapping("/getCandidateDetails/{cid}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public candidateSignup getCandidateDetails(@PathVariable String cid){
        return dservice.getCandidateDetails(cid);
    }

    @GetMapping("/getRecruiterDetails/{empid}")
    @PreAuthorize("hasAuthority('RECRUITER')")
    public recruiterSignup getRecruiterDetails(@PathVariable String empid){
        return dservice.getRecruiterDetails(empid);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
       
        resume attachment = null;
        attachment = dservice.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFile_type()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFile_name()
                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

    @GetMapping("/getResume/{pid}")
    public ResumeResponse getResumeByPid(@PathVariable String pid){
        resume resume= dservice.getResumeDetails(pid);
        ResumeResponse resumeResponse = new ResumeResponse();
        resumeResponse.setFile_name(resume.getFile_name());
        resumeResponse.setResumeid(resume.getResumeid());
        return resumeResponse;

    }


    @PostMapping("/getCandidateBySkills")
    public List<String> getCandidateBySkills(@RequestBody List<String> skills){

        return dservice.getCandidateByskills(skills);

    }

    @PostMapping("/sendMail")
    @PreAuthorize("hasAuthority('RECRUITER')")
    public void sendMail(@RequestBody RequestMail requestMail ){
        senderService.sendSimpleEmail(requestMail.getToemail(), requestMail.getSubject(), requestMail.getBody());
    }

    @GetMapping("/getRegisteredUser/{email}")
    public boolean getRegisteredUser(@PathVariable String email){
            return dservice.getRegisteredUser(email);
            
    }
 
}
