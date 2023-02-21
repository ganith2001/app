import { Component } from '@angular/core';

import { ServiceService } from '../service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  public user={name:'',email:'',password1:'',password2:'',otp:''};

  public cLogin={email:'',password:''};

  public rLogin={email:'',password:''};
 
  public config={
    leftTime:60,
    format:`ss`
  }
  visible=false;

  otpbtn="Generate OTP";
  data="hello";
  constructor(private service:ServiceService) {}

  onSignup(){
    if(this.user.password1==this.user.password2){
      this.service.createuser(this.user.name,this.user.email,this.user.password1,this.user.otp)
    }
    
    
  }

  generateOtp(){
    this.otpbtn="Resend OTP"
   this.visible=true;
   this.data=this.user.email;
    this.service.generateOtp("ganith2001@gmail.com");
  }

  onOtpChange(event:any){
    this.user.otp=event;
  }

  onCandidateLogin(){
    this.service.candidateLogin(this.cLogin.email,this.cLogin.password);
  }

  onRecruiterLogin(){
    this.service.recruiterLogin(this.rLogin.email,this.rLogin.password)
  }
}
