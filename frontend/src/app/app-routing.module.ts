import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { AddJobsComponent } from './add-jobs/add-jobs.component';
import { AddProfileComponent } from './add-profile/add-profile.component';
import { JobsComponent } from './jobs/jobs.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'recruiter/addjobs', component: AddJobsComponent },
  { path: 'candidate/addprofile', component: AddProfileComponent },
  { path: 'candidate/jobs' , component:JobsComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
