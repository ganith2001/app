import { Component } from '@angular/core';

import { ServiceService } from '../service.service';

@Component({
  selector: 'app-add-profile',
  templateUrl: './add-profile.component.html',
  styleUrls: ['./add-profile.component.css']
})
export class AddProfileComponent {

  public visible=false;
  public visible2=false;
  public collegeDetails = {college_name:"",degree:"",course:"",cgpa:0.0,passout_year:0}
  public collegeDetails3 = {college_name:"",degree:"",course:"",cgpa:0.0,passout_year:0}
  public collegeDetails2 = {college_name2:"",degree2:"",course2:"",cgpa2:0.0,passout_year2:0}
  public skill="";
  public profile = {address:"",experience:0,phone_no:"",cid:"",skills:new Array(),cDetails:new Array()}

  constructor(private service:ServiceService) {}

  addSkills(){
    this.profile.skills.push(this.skill);
  }

  addCollege1(){
    this.profile.cDetails.push(this.collegeDetails);
    this.visible=true;
  }

  addCollege2(){
    this.collegeDetails3.college_name=this.collegeDetails2.college_name2;
    this.collegeDetails3.degree=this.collegeDetails2.degree2;
    this.collegeDetails3.course=this.collegeDetails2.course2;
    this.collegeDetails3.cgpa=this.collegeDetails2.cgpa2;
    this.collegeDetails3.passout_year=this.collegeDetails2.passout_year2; 
    this.profile.cDetails.push(this.collegeDetails3);
    this.visible2=true;
  }

  onSubmit(){
    this.service.addProfile(this.profile);
  }

}
