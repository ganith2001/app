package com.spring_boot.backend.restapi.entities;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
@Table(name="candidate_profile",uniqueConstraints=@UniqueConstraint(name="uk_cid",columnNames=("cid")))
public class candidateProfile {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
    private String pId;
    private String phone_no;
    private String address;
    private int experience;

    @OneToOne
    @JoinColumn(name="cid")
    private candidateSignup cSignup;

    @OneToMany(targetEntity=candidateSkills.class,cascade=CascadeType.ALL)
    @JoinColumn(name="pId",referencedColumnName="pId")
    private List<candidateSkills> candidateSkills;

    @OneToMany(targetEntity=candidateCollegeDetail.class,cascade=CascadeType.ALL)
    @JoinColumn(name="pId",referencedColumnName="pId")
    private List<candidateCollegeDetail> candidateCollegeDetail;

     public candidateProfile(){}

    public candidateProfile(String pId, String address, int experience,String phone_no, candidateSignup cSignup) {
        this.pId=pId;
        this.phone_no = phone_no;
        this.address = address;
        this.experience = experience;
        this.cSignup = cSignup;
    }


    public candidateProfile(String pId, String phone_no, String address, int experience, candidateSignup cSignup,
           // List<com.spring_boot.backend.restapi.entities.candidateSkills> candidateSkills,
            List<com.spring_boot.backend.restapi.entities.candidateCollegeDetail> candidateCollegeDetail) {
        this.pId = pId;
        this.phone_no = phone_no;
        this.address = address;
        this.experience = experience;
        this.cSignup = cSignup;
       // this.candidateSkills = candidateSkills;
        this.candidateCollegeDetail = candidateCollegeDetail;
    }

    public candidateProfile(String phone_no, String address, int experience) {
        this.phone_no = phone_no;
        this.address = address;
        this.experience = experience;
    }

    public candidateProfile(String phone_no, String address, int experience, candidateSignup cSignup) {
        this.phone_no = phone_no;
        this.address = address;
        this.experience = experience;
        this.cSignup = cSignup;
  
    }

    

    public List<candidateSkills> getCandidateSkills() {
        return candidateSkills;
    }

    public void setCandidateSkills(List<candidateSkills> candidateSkills) {
        this.candidateSkills = candidateSkills;
    }

    public List<candidateCollegeDetail> getCandidateCollegeDetail() {
        return candidateCollegeDetail;
    }

    public void setCandidateCollegeDetail(List<candidateCollegeDetail> candidateCollegeDetail) {
        this.candidateCollegeDetail = candidateCollegeDetail;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
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

    public candidateSignup getcSignup() {
        return cSignup;
    }

    public void setcSignup(candidateSignup cSignup) {
        this.cSignup = cSignup;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }


}
