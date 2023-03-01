import { DataSource } from '@angular/cdk/collections';
import { Component } from '@angular/core';

import { ServiceService } from '../service.service';
import { Iprofiledetails } from '../types/profiletype';
import { Icollegedetails } from '../types/collegeDetailsType';
import { ICSkills } from '../types/candidateskills';
import { Idecoded } from '../types/decodestokentype';
import jwt_decode from 'jwt-decode';
import { Ichangepassword } from '../types/ChangePasswordType';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  public profile={phone_no:"",address:"",experience:0,cSignup:{cid: "", name: "", email: ""},candidateCollegeDetail:new Array<Icollegedetails>(),candidateSkills:new Array<ICSkills>(),pid:""};
  public phdisable=true;
  public addressdisable=true;
  public expdisable=true;
  public skilldisable=true;
  public updateProfile={phone_no:"",address:"",experience:0}
  public skill=""
  public clgid:String=""
  public updateCollege={
    college_name:"",
    degree:"",
    course:"",
    cgpa:0,
    passout_year:0
  }
  public resumedetails={resumeid:"",file_name:""}

  public files:any;
  public file2:String="";
  public token:Idecoded={Role: "", sub: "", iat: 0, exp: 0};
  public candidateDetails={cid: "",email: "",name:""}
  public visible=false;

  public changePassword={oldpassword:"",newpassword:"",confirmpassword:""}
  public changePasswordRequest:Ichangepassword={oldpassword:"",newPassword:""}
  public passwordResponse="";
  constructor(private service:ServiceService) { }

  ngOnInit() {

    this.service.getProfileDetails().subscribe(data=>{
      if(data!=null){
        this.profile=data;
        console.log(this.profile);
        this.service.getResumeDetails(this.profile.pid).subscribe(data=>{
        
        this.resumedetails=data;
        console.log(this.resumedetails);
        })
      }
     
        

    })

    const token = sessionStorage.getItem("token");
    if(token!=null){
      let decodedToken:Idecoded = jwt_decode(token);
      this.token=decodedToken;
      if(this.token.Role=="CANDIDATE"){
        this.service.getCandidateDetails(this.token.sub).subscribe(res=>{
          this.candidateDetails=res;
          console.log(this.candidateDetails);
        })
    }
  }

  
  }

  

  onUpdateProfile(pid:String){
    this.updateProfile.address=this.profile.address;
    this.updateProfile.phone_no=this.profile.phone_no;
    this.updateProfile.experience=this.profile.experience
    this.service.updateProfile(pid,this.updateProfile);
  }

  onAddSkill(pid:String){
    this.service.addSkills(pid,this.skill);
  }

  onDeleteSkill(pid:String,skill:String){
    this.service.deleteSkills(pid,skill);
  }

  onEdit(clg_id:String){
    this.clgid=clg_id;
  }

  onUpdateCollege(index:number){
      
      this.service.updateCollegeDetails(this.profile.candidateCollegeDetail[index].clg_id,this.profile.candidateCollegeDetail[index])
      
  }

  selectFile(event:any){
    if(event.target.files.length>0){
      const file = event.target.files[0];
      this.file2=file.name;
      this.files=file;
     
      
    }
  }
  
  onSubmit(cid:string){
    if(this.files!="No file chosen"){
    const formdata=new FormData();
    formdata.append('cid',cid);
    formdata.append('file',this.files);
    
    this.service.uploadResume(formdata);
   
  }
   
  }

  onDownload(resumeid:String){
    this.service.downloadResume(resumeid);

  }

  onDeleteResume(resumeid:String){
    this.service.deleteResume(resumeid);
  }

  onChange(){
      this.visible=true;
  }

  onUpdatePassword(){
    this.visible=false;
    if(this.changePassword.newpassword==this.changePassword.confirmpassword){
    
      this.changePasswordRequest.oldpassword=this.changePassword.oldpassword
      this.changePasswordRequest.newPassword=this.changePassword.newpassword
      this.service.changePassword(this.candidateDetails.cid,this.changePasswordRequest).subscribe(res=>{
        this.passwordResponse=res.error;
        
      })
      this.passwordResponse="Wrong password";

    }
    else{
      this.passwordResponse="New password and confirm password didnot match";
    }
  }

}
