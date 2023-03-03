import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { AddJobsComponent } from './add-jobs/add-jobs.component';
import { AddProfileComponent } from './add-profile/add-profile.component';
import { JobsComponent } from './jobs/jobs.component';
import { ProfileComponent } from './profile/profile.component';
import { AppliedJobsComponent } from './applied-jobs/applied-jobs.component';
import { CreatedJobsComponent } from './created-jobs/created-jobs.component';
import { AddRecruiterComponent } from './add-recruiter/add-recruiter.component';


import { AuthGuardService } from './auth-guard.service';
import { CandidateGuardService } from './candidate-guard.service';
import { RecruiterGuardService } from './recruiter-guard.service';
import { AdminGuardService } from './admin-guard.service';



var routes: Routes = [
  { path: 'login', component: LoginComponent,canActivate:[AuthGuardService] },
  { path: 'recruiter/addjobs', component: AddJobsComponent,canActivate:[RecruiterGuardService]},     
  { path: 'recruiter/createdJobs', component:CreatedJobsComponent,canActivate:[RecruiterGuardService]}, 
  { path: 'candidate/addprofile', component: AddProfileComponent,canActivate:[CandidateGuardService]},
  { path: 'candidate/jobs' , component:JobsComponent,canActivate:[CandidateGuardService]},
  { path: 'candidate/profile' , component:ProfileComponent,canActivate:[CandidateGuardService]},
  { path: 'candidate/appliedJobs' , component:AppliedJobsComponent,canActivate:[CandidateGuardService]}, 
  {path:'admin/addRecruiter', component:AddRecruiterComponent,canActivate:[AdminGuardService]},  
  {path:'',redirectTo:'/login',pathMatch:"full"},

  
];

/*
const token = sessionStorage.getItem("token");
    if(token!=null){
      
      let decodedToken:Idecoded = jwt_decode(token);
      if(decodedToken.Role=="RECRUITER"){
        var routes: Routes = [
          { path: 'recruiter/addjobs', component: AddJobsComponent },     
          { path: 'home', component:CreatedJobsComponent},    
          {path:'',redirectTo:'/home',pathMatch:"full"}     
        ];

      }
      else if(decodedToken.Role=="CANDIDATE"){
        var routes: Routes = [
          { path: 'candidate/addprofile', component: AddProfileComponent },
          { path: 'home' , component:JobsComponent},
          { path: 'candidate/profile' , component:ProfileComponent},
          { path: 'candidate/appliedJobs' , component:AppliedJobsComponent},   
          {path:'',redirectTo:'/home',pathMatch:"full"}     
        ];
      }
    }

*/


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
