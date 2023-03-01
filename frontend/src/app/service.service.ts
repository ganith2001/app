import { Injectable } from '@angular/core';

import { HttpClient,HttpHeaders} from '@angular/common/http';
import { ShowOnDirtyErrorStateMatcher } from '@angular/material/core';

import { Observable } from 'rxjs';

import { Itoken } from './types/tokentype';
import { Ijob } from './types/addjobtype';
import { Iprofile } from './types/addprofiletype';
import { Ialljobs } from './types/jobstype';
import { Iapply } from './types/applyjobtype';
import { Router } from '@angular/router';

import jwt_decode from 'jwt-decode';
import { Idecoded } from './types/decodestokentype';
import { IupdateProfile } from './types/updateProfileType';
import { Icollegedetails } from './types/collegeDetailsType';
import { Ichangepassword } from './types/ChangePasswordType';



@Injectable({
  providedIn: 'root'
})
export class ServiceService {




  constructor(private http:HttpClient,private router:Router) { }

   createuser(name:String,email:String,password1:String,otp:String):Observable<any>{
    var res:any;
    const data={email:email,name:name,password:password1,otp:otp}
   
    return this.http.post<any>("http://localhost:8080/candidateSignup",data)
    
    
  }

  generateOtp(email:String){
      console.log(email);
      this.http.post("http://localhost:8080/otpGenerate",email).subscribe(response=>{
      // res=response
      //  window.location.reload();
    });

  }

  candidateLogin(email:String,password:String):Observable<any>{
    
    const candidatelogin={email:email,password:password}
    return this.http.post<Itoken>("http://localhost:8080/candidateLogin",candidatelogin)
  }

  recruiterLogin(email:String,password:String):Observable<any>{
    const recruiterlogin={email:email,password:password}
    return this.http.post<Itoken>("http://localhost:8080/recruiterLogin",recruiterlogin)
  }

  addJobs(addJobs:Ijob){
    const token = sessionStorage.getItem("token");
 
    if(token!=null){
      
      let decodedToken:Idecoded = jwt_decode(token);
      addJobs.emp_id=decodedToken.sub;
      console.log(addJobs);
      this.http.post<any>("http://localhost:8080/addJobs",addJobs).subscribe(response=>{
         console.log(response);
         this.router.navigate(['/recruiter/createdJobs']);
    
       }
       )
    }
    
  }

  addProfile(addProfile:Iprofile){
    
    const token = sessionStorage.getItem("token");
 
    if(token!=null){
      
      let decodedToken:Idecoded = jwt_decode(token);
      addProfile.cid=decodedToken.sub;
      this.http.post<any>("http://localhost:8080/createProfile",addProfile).subscribe(response=>{
      console.log(response);
      this.router.navigate(['/candidate/profile']);
    
    }
    )
  }

  }

  getAllJobs():Observable<Ialljobs>{
    //let token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQ0FORElEQVRFIiwic3ViIjoiMDUzN2IzMjgtMjAwMC00NDMwLWFlYTQtOTQ1MWExZDZlZWZmIiwiaWF0IjoxNjc3NDc5OTQxLCJleHAiOjE2Nzc0ODE3NDF9.1wU6RypyI0x-g9iwO5GoQkAlXTgjJT2Eu0lxQZh1H0g"
    //let head_obj=new HttpHeaders().set("Authorization",token)
    return this.http.get<Ialljobs>("http://localhost:8080/getAllJobs");

  }

  getCreatedJobs():Observable<Ialljobs>{
    var cid="";
    const token = sessionStorage.getItem("token");
 
    if(token!=null){
      
      let decodedToken:Idecoded = jwt_decode(token);
       cid=decodedToken.sub.toString();
      
    }
    
   
    return this.http.get<Ialljobs>("http://localhost:8080/getJobsByEmpId/"+cid);
    
  }

  applyJobs(job_id:String){
    const token = sessionStorage.getItem("token");
 
    if(token!=null){
      let decodedToken:Idecoded = jwt_decode(token);
      var cid=decodedToken.sub.toString();
      const applyJob={cid:cid,job_id:job_id}
      this.http.post("http://localhost:8080/applyJobs",applyJob).subscribe(response=>{
        console.log(response);
        window.location.reload();
       
      }
      )
    }
  }

  getAppliedJobIds():Observable<any>{
    const token = sessionStorage.getItem("token");
    var cid="";
    if(token!=null){
      let decodedToken:Idecoded = jwt_decode(token);
      cid=decodedToken.sub.toString();
      
    }
    return this.http.get("http://localhost:8080/getAppliedJobsIdByCid/"+cid);
  }

  getAppliedJobs():Observable<any>{
    const token = sessionStorage.getItem("token");
    var cid="";
    if(token!=null){
      let decodedToken:Idecoded = jwt_decode(token);
      cid=decodedToken.sub.toString();
      
    }
    return this.http.get("http://localhost:8080/getAppliedJobsByCid/"+cid);
  }

  getProfileDetails():Observable<any>{
    const token = sessionStorage.getItem("token");
    var cid="";
    if(token!=null){
      let decodedToken:Idecoded = jwt_decode(token);
      cid=decodedToken.sub.toString();
      
    }
   return this.http.get("http://localhost:8080/getProfile/"+cid);
  }

  getCandiateDetails(job_id:String):Observable<any>{

    return this.http.get("http://localhost:8080/getCandidatesByJobid/"+job_id);
  }

  getShortlistedCandidates(job_id:String){
    return this.http.get("http://localhost:8080/getAppliedjobsIdJobAndShortLists/"+job_id);

  }

  updateStatus(cid:String,job_id:String){
    this.http.put("http://localhost:8080/updateStatus/"+cid+"/"+job_id,"Shortlisted").subscribe(res=>{
      console.log(res);
      window.location.reload();
    })
  }

  updateProfile(pid:String,updateProfile:IupdateProfile){
    console.log(updateProfile);
    this.http.put("http://localhost:8080/updateProfile/"+pid,updateProfile).subscribe(res=>{
      console.log(res);
      window.location.reload();
    });
  }

  addSkills(pid:String,skill:String){
    this.http.put("http://localhost:8080/updateSkills/"+pid,skill).subscribe(res=>{
      console.log(res);
      window.location.reload();
    });
  }

  deleteSkills(pid:String,skill:String){
    this.http.delete("http://localhost:8080/deleteSkills/"+pid+"/"+skill).subscribe(res=>{
      console.log(res);
      window.location.reload();
    });

  }

  updateCollegeDetails(clgid:String,college_details:Icollegedetails){
    var collegedetailsRequest={college_name:college_details.college_name,degree:college_details.degree,course:college_details.course,cgpa:college_details.cgpa,passout_year:college_details.passout_year}

    this.http.put("http://localhost:8080/updateCollege/"+clgid,collegedetailsRequest).subscribe(res=>{
      console.log(res);
      window.location.reload();
    });

  }

  uploadResume(formdata:FormData){
    this.http.post("http://localhost:8080/upload",formdata).subscribe(res=>{
      console.log(res);
      window.location.reload();
    });

  }

  downloadResume(resumeid:String){
    this.http.get("http://localhost:8080/download/"+resumeid,{observe:'response',responseType:'blob'}).subscribe(data=>{
      
  
      let blob:Blob=data.body as Blob;
      let a = document.createElement('a');
      //a.download="Resume";
      a.href=window.URL.createObjectURL(blob);
      a.click();
    });
  }

  deleteResume(resumeid:String){
    this.http.delete("http://localhost:8080/deleteResume/"+resumeid).subscribe(data=>{
      window.location.reload();
    })

  }

  getResumeDetails(pid:String):Observable<any>{
   
    return this.http.get("http://localhost:8080/getResume/"+pid);
  }

  deleteJobs(jobid:String){
    this.http.delete("http://localhost:8080/deleteJobsByJobId/"+jobid).subscribe(res=>{
      console.log(res);
      window.location.reload();
    })
  }

  getRecuiterDetails(empid:String):Observable<any>{
    return this.http.get("http://localhost:8080/getRecruiterDetails/"+empid)
  }

  getCandidateDetails(cid:String):Observable<any>{
    return this.http.get("http://localhost:8080/getCandidateDetails/"+cid)
  }

  changePassword(cid:string,changePassword:Ichangepassword):Observable<any>{
   
    
    return this.http.put("http://localhost:8080/changePassword/"+cid,changePassword)
  }
}
