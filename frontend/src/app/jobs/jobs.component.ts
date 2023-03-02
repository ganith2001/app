import { Component, ComponentFactoryResolver } from '@angular/core';

import { ServiceService } from '../service.service';
import { Ialljobs } from '../types/jobstype';
import { Icollegedetails } from '../types/collegeDetailsType';
import { ICSkills } from '../types/candidateskills';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent {

  public jobs:Array<Ialljobs>=new Array();
  public appliedJobs:Array<String>=new Array();
  public profile={phone_no:"",address:"",experience:0,cSignup:{cid: "", name: "", email: ""},candidateCollegeDetail:new Array<Icollegedetails>(),candidateSkills:new Array<ICSkills>(),pid:""};
  public date:any;
  public searched:String="";
  
  constructor(private service:ServiceService) { }

  ngOnInit() {
    this.date=formatDate(new Date(), 'yyyy-MM-dd', 'en');

    this.service.getAllJobs().subscribe( data =>{

      this.jobs= Object.values(data);
      console.log(this.jobs);

    })

    this.service.getAppliedJobIds().subscribe(data=>{
      this.appliedJobs=Object.values(data);
      console.log(this.appliedJobs);

    })

    this.service.getProfileDetails().subscribe(data=>{
      if(data!=null){
        this.profile=data;
        console.log(this.profile);
      }

    })

  }

  onApply(job_id:String){
    this.service.applyJobs(job_id);
  }

  

}
