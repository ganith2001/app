import { Component } from '@angular/core';

import { ServiceService } from '../service.service';
import { Ialljobs } from '../types/jobstype';
import { Iprofiledetails } from '../types/profiletype';



export interface Tile {
  color: string;
  cols: number;
  rows: number;
  text: string;
}

@Component({
  selector: 'app-created-jobs',
  templateUrl: './created-jobs.component.html',
  styleUrls: ['./created-jobs.component.css']
})
export class CreatedJobsComponent {
  public jobs:Array<Ialljobs>=new Array();
  public job_id:string="";
  public profiles:Array<Iprofiledetails>=new Array();
  public jobid:String="";
  public shorlisted:Array<String>=new Array();
  public resumedetails={resumeid:"",file_name:""}

  tiles: Tile[] = [
    {text: 'One', cols: 1, rows: 1, color: 'lightblue'},
    {text: 'Two', cols: 1, rows: 1, color: 'lightgreen'},
    {text: 'Three', cols: 1, rows: 1, color: 'lightpink'},
    {text: 'Four', cols: 1, rows: 1, color: '#DDBDF1'},
    {text: 'Four', cols: 2, rows: 1, color: '#DDBDF1'},
    {text: 'Four', cols: 2, rows: 1, color: '#DDBDF1'},
    {text: 'Two', cols: 4, rows: 2, color: 'lightgreen'}
  ];

  constructor(private service:ServiceService) { }

  ngOnInit() {
      this.service.getCreatedJobs().subscribe(data=>{
        this.jobs= Object.values(data);
          console.log(this.jobs);
      })

      
  }

  view(job_id:String){
    var job=job_id;
      this.service.getCandiateDetails(job_id).subscribe(data=>{
        this.profiles=Object.values(data);
        console.log(this.profiles);
      })

      this.service.getShortlistedCandidates(job_id).subscribe(data=>{
        this.shorlisted=Object.values(data);
        this.jobid=job_id;
        console.log(this.shorlisted);
      })
  }

  Shortlist(cid:String,jobid:String){
      this.service.updateStatus(cid,jobid);
  }

  onDownload(pid:String){
    this.service.getResumeDetails(pid).subscribe(data=>{
        
      this.resumedetails=data;
      console.log(this.resumedetails);
      this.service.downloadResume(this.resumedetails.resumeid);
  })
    //

  }

  onDeleteJob(job_id:String){
    this.service.deleteJobs(job_id);
  }
}
