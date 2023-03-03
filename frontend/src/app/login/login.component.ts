import { Component } from '@angular/core';

import { ServiceService } from '../service.service';
import { Router } from '@angular/router';
import { IForgetPassword } from '../types/forgetpasswordrequesttype';
import jwt_decode from 'jwt-decode';
import { Idecoded } from '../types/decodestokentype';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  public user={name:'',email:'',password1:'',password2:'',otp:''};
  public forgotPassword={email:'',password1:'',password2:'',otp:''}
  public cLogin={email:'',password:''};

  public rLogin={email:'',password:''};

  public candidateError="";
  public recruiterError="";
  public registerEmailError="";
  public forgotPasswordError="";
  public res="";
  public forgetPasswordRequest:IForgetPassword={password:"",otp:""}


  forgotPasswordVisible=false;
  otpVisible=false;
  
 
  public config={
    leftTime:60,
    format:`ss`
  }
  visible=false;

  otpbtn="Generate OTP";
  otpbtn2="Generate OTP";
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
    this.service.getRegisteredUser(this.user.email).subscribe(res=>{
      if(res==false){
      this.otpbtn="Resend OTP"
      this.visible=true;
      this.data=this.user.email;
        this.service.generateOtp(this.data);
      }
      else if(res==true){
        this.registerEmailError="This email is already registered"
      }
    })
      
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
          let decodedToken:Idecoded = jwt_decode(response.token);
          sessionStorage.setItem('token',response.token)
          if(decodedToken.Role=="ADMIN"){
            this.router.navigate(['/admin/addRecruiter']);
          }
          else{
            this.router.navigate(['/recruiter/createdJobs']);
          }
          setTimeout(()=>{  window.location.reload() }, 100)
        }
        else{
          this.recruiterError="Incorrect Email or Password !"
        }
      }
      ) 
  }

  onForgotPassword(){
    this.candidateError="";
    this.forgotPasswordVisible=true;
  }

  generateOtp2(){
    
    if(this.forgotPassword.email!=''){
      this.service.getRegisteredUser(this.forgotPassword.email).subscribe(res=>{
        if(res==true){
        this.forgotPasswordError=""
        this.otpbtn2="Resend OTP"
        this.otpVisible=true;
        this.service.generateOtp(this.forgotPassword.email);
        }
        else if(res==false){
          this.forgotPasswordError="This Email is not registered"
        }
      })
        
     }
     else{
          this.forgotPasswordError="Please Enter The EmailId !"
     }
   
  }

  onOtpChange2(event:any){
    this.forgotPassword.otp=event;
  }

  onChangePassword(){
    const regex = new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$');
    const valid = regex.test(this.forgotPassword.password1);
    if(valid){
      if(this.forgotPassword.email!="" && this.forgotPassword.password1!="" && this.forgotPassword.password2!="" ){
        if(this.forgotPassword.password1==this.forgotPassword.password2){
            this.forgetPasswordRequest.password=this.forgotPassword.password1
            this.forgetPasswordRequest.otp=this.forgotPassword.otp
            this.service.forgetPassword(this.forgotPassword.email,this.forgetPasswordRequest).subscribe(response=>{
              this.forgotPasswordError=response.res;
              if(response.res=="Password changed successfully"){
                
                this.forgotPasswordVisible=false;
                this.otpbtn="Generate OTP"
                this.forgotPassword.email=""
                this.forgotPassword.password1=""
                this.forgotPassword.password2=""
                this.forgotPassword.otp=""
              }
            });
            
        }
        else{
          this.forgotPasswordError="New Password and Confirm Password do not match"
        }
       
     
    }  
    else{
      this.forgotPasswordError="Invalid Data"
    }
    }
    else{
      this.forgotPasswordError="Password should have minimum 8 characters,at least 1 uppercase,1 lowercase letter and 1 number";
    }
  }



}
