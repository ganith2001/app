import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatToolbarModule} from '@angular/material/toolbar';
import { LoginComponent } from './login/login.component';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button'

import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import {MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import {MatExpansionModule} from '@angular/material/expansion';

import { HttpClientModule , HTTP_INTERCEPTORS} from '@angular/common/http';
import { NgOtpInputModule } from  'ng-otp-input';
import { CountdownModule } from 'ngx-countdown';
import { AddJobsComponent } from './add-jobs/add-jobs.component';
import { AddProfileComponent } from './add-profile/add-profile.component';
import { JobsComponent } from './jobs/jobs.component';
import { ProfileComponent } from './profile/profile.component';

import {MatTableModule} from '@angular/material/table';
import { AppliedJobsComponent } from './applied-jobs/applied-jobs.component';
import { CreatedJobsComponent } from './created-jobs/created-jobs.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatMenuModule} from '@angular/material/menu';
import { TokenInterceptorService } from './token-interceptor.service';

import { AuthGuardService } from './auth-guard.service';
import { CandidateGuardService } from './candidate-guard.service';
import { RecruiterGuardService } from './recruiter-guard.service';



@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    AddJobsComponent,
    AddProfileComponent,
    JobsComponent,
    ProfileComponent,
    AppliedJobsComponent,
    CreatedJobsComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatTabsModule,
    MatIconModule,
    FormsModule,
    HttpClientModule,
    NgOtpInputModule,
    CountdownModule,
    MatExpansionModule,
    MatTableModule,
    MatGridListModule,
    MatMenuModule

  ],
  providers: [AuthGuardService,CandidateGuardService,RecruiterGuardService,{
    provide:HTTP_INTERCEPTORS,
    useClass:TokenInterceptorService,
    multi:true 
  }
],
  bootstrap: [AppComponent]
})
export class AppModule { }
