import { Component } from '@angular/core';

import { ServiceService } from '../service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  public user={name:'',email:'',password1:'',password2:'',otp:''};

  public cLogin={email:'',password:''};

  public rLogin={email:'',password:''};

  public candidateError="";
  public recruiterError="";
  public registerEmailError="";
  public res="";
  
 
  public config={
    leftTime:60,
    format:`ss`
  }
  visible=false;

  otpbtn="Generate OTP";
  data="";
  constructor(private service:ServiceService,private router:Router) {}

  onSignup(){
    const regex = new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$');
    const valid = regex.test(this.user.password1);
    if(valid){
      if(this.user.password1==this.user.password2 && this.user.name!="" && this.user.email!="" && this.user.password1!="" && this.user.password2!="" ){
        this.service.createuser(this.user.name,this.user.email,this.user.password1,this.user.otp)
        .subscribe(response=>{
          this.res=response.res
          console.log(this.res) 
          this.visible=false;
          this.otpbtn="Generate OTP"
          this.user.name=""
          this.user.email=""
          this.user.password1=""
          this.user.password2=""
          this.user.otp=""
        // window.location.reload();
       
    })
     
    }  
    else{
      this.res="Invalid Data"
    }
    }
    else{
      this.res="Password should have minimum 8 characters,at least 1 uppercase,1 lowercase letter and 1 number";
    }
  }

  generateOtp(){
   if(this.user.email!=''){
      this.otpbtn="Resend OTP"
    this.visible=true;
    this.data=this.user.email;
      this.service.generateOtp(this.data);
   }
   else{
        this.registerEmailError="Please Enter The EmailId !"
   }
  }

  onOtpChange(event:any){
    this.user.otp=event;
  }

  onCandidateLogin(){
   
      this.service.candidateLogin(this.cLogin.email,this.cLogin.password).subscribe(response=>{
        
        console.log(response.message);
        if(response.message=="Successfull"){
          sessionStorage.setItem('token',response.token)
          this.router.navigate(['/candidate/jobs']);
          setTimeout(()=>{  window.location.reload() }, 100)
          
        }
        else{
          this.candidateError="Incorrect Email or Password !"
        }
      }
      )
    
  }

  onRecruiterLogin(){
   
      this.service.recruiterLogin(this.rLogin.email,this.rLogin.password).subscribe(response=>{
        console.log(response.message);
        if(response.message=="Successfull"){
          sessionStorage.setItem('token',response.token)
          this.router.navigate(['/recruiter/createdJobs']);
          setTimeout(()=>{  window.location.reload() }, 100)
        }
        else{
          this.recruiterError="Incorrect Email or Password !"
        }
      }
      )
    
   
  }
}
