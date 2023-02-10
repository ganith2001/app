package com.spring_boot.backend.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring_boot.backend.restapi.entities.applied_jobs;
import com.spring_boot.backend.restapi.entities.apply;
import com.spring_boot.backend.restapi.entities.jobs;
import com.spring_boot.backend.restapi.entities.login;
import com.spring_boot.backend.restapi.entities.profile;
import com.spring_boot.backend.restapi.entities.profile_details;
import com.spring_boot.backend.restapi.entities.resume;
import com.spring_boot.backend.restapi.model.ResponseData;
import com.spring_boot.backend.restapi.services.dataservice;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class dataController {

    @Autowired
    private dataservice dservice;

    @RequestMapping(method=RequestMethod.POST,value="/createuser")
    public void add(@RequestBody login l){
        dservice.addlogin(l);
    }

    @RequestMapping(method=RequestMethod.POST,value="/createjobs")
    public void addjob(@RequestBody jobs j){
        dservice.addjobs(j);
    }

    @RequestMapping(method=RequestMethod.GET,value="/getjobs")
    public List<jobs> getjob(){
        return dservice.getjobs();
    }

    
    @RequestMapping(method=RequestMethod.GET,value="/getappliedjobs/{email}")
    public List<apply> getappliedjob(@PathVariable String email){
        return dservice.getappliedjobs(email);
    }

    @PostMapping("/applyjob")
    public void apply(@RequestBody applied_jobs a){
        dservice.applyjobs(a);
    }

    @GetMapping("/getprofilebyempidjobrole/{empid}/{jobrole}")
    public List<profile_details> getProfile(@PathVariable String empid,@PathVariable String jobrole){
        return dservice.getprofilebyempid(empid, jobrole);
    }

    @PutMapping("/updatestatus/{email}/{jobid}")
    public void updateStatus(@PathVariable String email,@PathVariable int jobid,@RequestBody String status){
        dservice.updateStatus(email, jobid,status);
    }

    @PostMapping("/addprofile")
    public void addProfile(@RequestBody profile p){
        dservice.addProfile(p);

    }

    @GetMapping("/getprofilebyuser/{email}")
    public List<profile> getProfileByUser(@PathVariable String email){
        return dservice.getProfileByUser(email);
    }

    @DeleteMapping("/deletejob/{id}")
    public void deleteJobById(@PathVariable int id){
        dservice.deleteJobById(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody login loginRequest ){

        return dservice.login(loginRequest);
    }


    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        resume attachment = null;
        String downloadURl = "";
        attachment = dservice.saveAttachment(file);
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

}
