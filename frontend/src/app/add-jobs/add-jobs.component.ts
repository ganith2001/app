import { Component } from '@angular/core';
import { ServiceService } from '../service.service';

import { Ijob } from '../types/addjobtype';
@Component({
  selector: 'app-add-jobs',
  templateUrl: './add-jobs.component.html',
  styleUrls: ['./add-jobs.component.css']
})
export class AddJobsComponent {
  public addJobs:Ijob = {job_role:"",ctc:0,location:"",apply_by:"",description:"",cgpa:0.0,experiance:0,emp_id:"",skills:new Array()}
  public skill=""

  constructor(private service:ServiceService) {}

  onAddSkills(){
    this.addJobs.skills.push(this.skill);
   
  }

  onSubmit(){
      this.service.addJobs(this.addJobs);
  }

}
