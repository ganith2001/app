import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { ShowOnDirtyErrorStateMatcher } from '@angular/material/core';

import { Observable } from 'rxjs';

import { Itoken } from './types/tokentype';
import { Ijob } from './types/addjobtype';
import { Iprofile } from './types/addprofiletype';
import { Ialljobs } from './types/jobstype';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {



  constructor(private http:HttpClient) { }

   createuser(name:String,email:String,password1:String,otp:String){
    var res:any;
    const data={email:email,name:name,password:password1,otp:otp}
   
    this.http.post<any>("http://localhost:8080/candidateSignup",data)
    .subscribe(response=>{
        res=response
        console.log(res) 
        return res;
       window.location.reload();
    })
    
  }

  generateOtp(email:String){
      console.log(email);
      this.http.post("http://localhost:8080/otpGenerate",email).subscribe(response=>{
      // res=response
      //  window.location.reload();
    });

  }

  candidateLogin(email:String,password:String){
    const candidatelogin={email:email,password:password}
    this.http.post<Itoken>("http://localhost:8080/candidateLogin",candidatelogin).subscribe(response=>{
      console.log(response.token);
      if(response.message=="Successfull"){
        sessionStorage.setItem('token',response.token)
      }

    }
    )
  }

  recruiterLogin(email:String,password:String){
    const recruiterlogin={email:email,password:password}
    this.http.post<Itoken>("http://localhost:8080/recruiterLogin",recruiterlogin).subscribe(response=>{
      console.log(response.token);
      if(response.message=="Successfull"){
        sessionStorage.setItem('token',response.token)
      }
    }
    )
  }

  addJobs(addJobs:Ijob){

    addJobs.emp_id="NC1000";
    console.log(addJobs);
   this.http.post<any>("http://localhost:8080/addJobs",addJobs).subscribe(response=>{
      console.log(response);
     
    }
    )

  }

  addProfile(addProfile:Iprofile){
    addProfile.cid="7ba3a9f9-84d4-471a-af47-8b32fd2f672d";
    console.log(addProfile);
    this.http.post<any>("http://localhost:8080/createProfile",addProfile).subscribe(response=>{
      console.log(response);
     
    }
    )

  }

  getAllJobs():Observable<Ialljobs>{
    return this.http.get<Ialljobs>("http://localhost:8080/getAllJobs");

  }
 
}
