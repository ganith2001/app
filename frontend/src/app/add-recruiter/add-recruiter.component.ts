import { Component } from '@angular/core';
import { ServiceService } from '../service.service';
import { IRecruiters } from '../types/RecruitersResponse';
@Component({
  selector: 'app-add-recruiter',
  templateUrl: './add-recruiter.component.html',
  styleUrls: ['./add-recruiter.component.css']
})
export class AddRecruiterComponent {
  public recruiter={emp_id:"",email:"",name:"",password:""}
  public getRecruters: Array<IRecruiters>=new Array();
  constructor(private service:ServiceService) {}
  ngOnInit(){
    this.service.getAllRecruiters().subscribe(res=>{

      this.getRecruters=res;
    })
  }

  onSubmit(){
      this.service.addRecruiter(this.recruiter);
  }
}
