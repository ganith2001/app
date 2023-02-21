package com.spring_boot.backend.restapi.services;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_boot.backend.restapi.repositories.candidateSignupRepository;
import com.spring_boot.backend.restapi.repositories.resumeRepository;

import com.spring_boot.backend.restapi.repositories.*;
import com.spring_boot.backend.restapi.model.*;
import com.spring_boot.backend.restapi.entities.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Random;

import java.time.Instant;

@Service
public class dataservice {
    @Autowired
	private EmailSenderService senderService;

    @Autowired
    private candidateSignupRepository cSRepository ;

    @Autowired
    private recruiterSignupRepository rSRepository;

    @Autowired
    private candidateProfileRepository cPRepository;

    @Autowired
    private candidateSkillsRepository cSkRepository;

    @Autowired
    private candidateCollegeRepository cCRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RequiredSkillsRepository requiredSkillsRepository;

    @Autowired
    private AppliedJobRepository appliedJobRepository;

    
    @Autowired
    private PasswordEncoder passwordEncoder;

    

    //@Autowired
    //private JwtUtils jwt;

    @Autowired
    private resumeRepository rRepository;


    public String addCandidate(candidateSignupRequest inp,String OTP,Long timestamp){
        
       Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();
        //System.out.println(timeStampSeconds-timestamp);
      

        if(OTP.equalsIgnoreCase(inp.getOtp()) && timeStampSeconds-timestamp<=60 ){
            inp.setPassword(passwordEncoder.encode(inp.getPassword()));
            candidateSignup candidateSignup = new candidateSignup();
            candidateSignup.setName(inp.getName());
            candidateSignup.setEmail(inp.getEmail());
            candidateSignup.setPassword(inp.getPassword());
            candidateSignup a=this.cSRepository.save(candidateSignup);
        
        if(a!=null){
            return "User created successfully";
        }
        return "User not created";
        } 
        
   return "OTP is incorrect";
    }

  

    public candidateSignup candidateLogin(AuthRequest authRequest) {

        candidateSignup user = this.cSRepository.findByEmail(authRequest.getEmail());

        if(user == null){
            return null;
        }
        else if(!passwordEncoder.matches(authRequest.getPassword(),user.getPassword() )){
            return null;
        }

        return user;
          
    } 
          
    

    public String addRecruiter(recruiterSignup inp){
        inp.setPassword(passwordEncoder.encode(inp.getPassword()));
        recruiterSignup a=this.rSRepository.save(inp);
        if(a!=null){
            return "User created successfully";
        }
        return "User not created";
    }

    public recruiterSignup recruiterLogin(AuthRequest authRequest) {
      
        recruiterSignup user = this.rSRepository.findByEmail(authRequest.getEmail());

    
        if(user == null){
            return null;
        }
        else if(!passwordEncoder.matches(authRequest.getPassword(),user.getPassword() )){
            return null;
        }

        return user;
          
    } 

    public void createProfile(createProfileRequest cPRequest){
    /*     candidateSignup candidateSignup = new candidateSignup(cPRequest.getcid());
        List<candidateSkills> skills = new ArrayList<>();
        for (String sk:cPRequest.getSkills()){
            candidateSkills cSkills = new candidateSkills(new candidateSkillsId(sk));
            skills.add(cSkills);
        }

        List<candidateCollegeDetail> collegeDetails = new ArrayList<>();
        for(collegeDetailsRequest cD:cPRequest.getcDetails()){
            candidateCollegeDetail cDetail = new candidateCollegeDetail(cD.getCollege_name(),cD.getDegree(),cD.getCourse(),cD.getCgpa(),cD.getPassout_year());
            collegeDetails.add(cDetail);
        }

        candidateProfile candidateProfile = new candidateProfile(cPRequest.getPhone_no(),cPRequest.getAddress(),cPRequest.getExperience(),candidateSignup,skills,collegeDetails);
     */  
   // System.out.println(cPRequest.getCandidateProfile().getCandidateSkills());
   //  this.cPRepository.save(cPRequest.getCandidateProfile());
   //System.out.println(cPRequest.getSkills());
          candidateSignup cSignup = new candidateSignup(cPRequest.getcid(),null,null,null);
        candidateProfile cProfile = new candidateProfile(cPRequest.getPhone_no(),cPRequest.getAddress(),cPRequest.getExperience(), cSignup);
        cProfile=this.cPRepository.save(cProfile);
        List<candidateSkills> skills = new ArrayList<>();
        for (String sk:cPRequest.getSkills()){
            candidateSkills cSkills = new candidateSkills(new candidateSkillsId(cProfile,sk));
            skills.add(cSkills);
        }
        this.cSkRepository.saveAll(skills);

        List<candidateCollegeDetail> collegeDetails = new ArrayList<>();
        for(collegeDetailsRequest cD:cPRequest.getcDetails()){
            candidateCollegeDetail cDetail = new candidateCollegeDetail(cD.getCollege_name(),cD.getDegree(),cD.getCourse(),cD.getCgpa(),cD.getPassout_year(),cProfile);
            collegeDetails.add(cDetail);
        }
        this.cCRepository.saveAll(collegeDetails);
       
    }



    public resume saveAttachment(MultipartFile file,String cid) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //candidateSignup cSignup = new candidateSignup(cid,null,null,null);
        //cSignup.setcid(cid);
        candidateProfile cProfile=this.cPRepository.findByCId(cid);
      
        try {
             if(fileName.contains("..")) {
                 throw  new Exception("Filename contains invalid path sequence "
                 + fileName);
             }
             
             
           candidateProfile profile = new candidateProfile(cProfile.getPId(),null,0,  null, null);
                resume attachment
                     = new resume(fileName,
                     file.getContentType(),
                     file.getBytes(),profile);

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


    

    public void addJobs(JobsRequest jobRequest){
        
        recruiterSignup rSignup = new recruiterSignup(jobRequest.getEmp_id(),null,null,null);
        Job job = new Job(jobRequest.getJob_role(),jobRequest.getCtc(),jobRequest.getLocation(),jobRequest.getApply_by(),jobRequest.getCgpa(),jobRequest.getExperiance(),jobRequest.getDescription(),rSignup);
        job=this.jobRepository.save(job);

        List<RequiredSkills> requiredSkills = new ArrayList<>();

        for(String sk:jobRequest.getSkills()){
            RequiredSkills rSkills = new RequiredSkills(new RequiredSkillsId(job,sk));
            requiredSkills.add(rSkills);
        }

        this.requiredSkillsRepository.saveAll(requiredSkills);

    }

    public void applyjobs(ApplyJobsRequest appliedJobRequest){
        candidateSignup cSignup = new candidateSignup(appliedJobRequest.getcid());
        Job job = new Job(appliedJobRequest.getJob_id());

        AppliedJob appliedJob = new AppliedJob(new AppliedJobsId(cSignup,job));
        this.appliedJobRepository.save(appliedJob);
    }

    public void updateStatus(String cid,String job_id,String status){
        candidateSignup cSignup = new candidateSignup(cid);
        Job job = new Job(job_id);

        AppliedJob appliedJob = new AppliedJob(new AppliedJobsId(cSignup,job),status);
        this.appliedJobRepository.save(appliedJob);

        senderService.sendSimpleEmail("vganith@ninjacart.com",
				"This is email body",
				"This is email subject");
    }

    public candidateProfile getProfile(String cid){
        candidateProfile list = (candidateProfile) this.cPRepository.findBycSignupCid(cid);
        return list;
    }

    public List<Job> getAllJobs(){
        List<Job> jobs = (List<Job>)jobRepository.findAll();
        return jobs;
      
    }

    public List<Job> getJobsByEmpid(String empid){
        return  jobRepository.findByrecruiterSignupEmpid(empid);
    }

    public List<AppliedJob> getAppliedJobsByCid(String cid){
        return appliedJobRepository.findByappliedJobsIdCandidateCid(cid);
    }

    public List<candidateProfile> getCandidatesByJobid(String job_id){
        List<String> cid = appliedJobRepository.findByappliedJobsIdJobJobid(job_id);
        return cPRepository.findByCIds(cid);
        

    }


     public void deleteJobById(String empid){
     
        this.jobRepository.deleteByrecruiterSignupEmpid(empid);
    }
    


}
