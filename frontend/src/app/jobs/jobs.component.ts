import { Component, ComponentFactoryResolver } from '@angular/core';

import { ServiceService } from '../service.service';
import { Ialljobs } from '../types/jobstype';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent {

  public jobs:Array<Ialljobs>=new Array();
  public appliedJobs:Array<String>=new Array();

  constructor(private service:ServiceService) { }

  ngOnInit() {

    this.service.getAllJobs().subscribe( data =>{

      this.jobs= Object.values(data);
      console.log(this.jobs);

    })

    this.service.getAppliedJobIds().subscribe(data=>{
      this.appliedJobs=Object.values(data);
      console.log(this.appliedJobs);

    })

  }

  onApply(job_id:String){
    this.service.applyJobs(job_id);
  }

  

}
