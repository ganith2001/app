import { DataSource } from '@angular/cdk/collections';
import { Component } from '@angular/core';

import { ServiceService } from '../service.service';
import { Iprofiledetails } from '../types/profiletype';
import { Icollegedetails } from '../types/collegeDetailsType';
import { ICSkills } from '../types/candidateskills';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  public CollegeDetail:Array<Icollegedetails>=[{college_name: "", degree: "", course: "", cgpa: 0, passout_year: 0}]
  public profile={phone_no:"",address:"",experience:0,cSignup:{cid: "", name: "", email: ""},candidateCollegeDetail:this.CollegeDetail,candidateSkills:new Array<ICSkills>(),pid:""};


  constructor(private service:ServiceService) { }

  ngOnInit() {

    this.service.getProfileDetails().subscribe(data=>{
     this.profile=data;
      console.log(this.profile);
    })
  }

}
