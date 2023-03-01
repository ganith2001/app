import { Component } from '@angular/core';

import { ServiceService } from '../service.service';
import { IappliedJobs } from '../types/apliedjobs';

@Component({
  selector: 'app-applied-jobs',
  templateUrl: './applied-jobs.component.html',
  styleUrls: ['./applied-jobs.component.css']
})
export class AppliedJobsComponent {

  public appliedJobs:Array<IappliedJobs>=new Array();

  constructor(private service:ServiceService) { }

  ngOnInit(){
      this.service.getAppliedJobs().subscribe(data=>{
        this.appliedJobs=Object.values(data);
        console.log(this.appliedJobs[0]);
      })
  }


}
