package com.spring_boot.backend.restapi.model;

import java.util.List;


public class createProfileRequest {
/* 
    private candidateProfile candidateProfile;

    public createProfileRequest(){

    }

    public createProfileRequest(com.spring_boot.backend.restapi.entities.candidateProfile candidateProfile) {
        this.candidateProfile = candidateProfile;
    }

    public candidateProfile getCandidateProfile() {
        return candidateProfile;
    }

    public void setCandidateProfile(candidateProfile candidateProfile) {
        this.candidateProfile = candidateProfile;
    }

    
    */

     private String address;
    private int experience;
    private String phone_no;
    private String cid;
    private List<String> skills;
    private List<collegeDetailsRequest> cDetails;

    public createProfileRequest() {}


    public createProfileRequest(String address, int experience, String phone_no, String cid, List<String> skills,
            List<collegeDetailsRequest> cDetails) {
        this.address = address;
        this.experience = experience;
        this.phone_no = phone_no;
        this.cid = cid;
        this.skills = skills;
        this.cDetails = cDetails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getcid() {
        return cid;
    }

    public void setcid(String cid) {
        this.cid = cid;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }


    public List<collegeDetailsRequest> getcDetails() {
        return cDetails;
    }


    public void setcDetails(List<collegeDetailsRequest> cDetails) {
        this.cDetails = cDetails;
    }

}
