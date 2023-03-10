import { Injectable } from '@angular/core';

import { Router, CanActivate, ActivatedRouteSnapshot,RouterStateSnapshot } from '@angular/router';
import jwt_decode from 'jwt-decode';
import { Idecoded } from './types/decodestokentype';

@Injectable({
  providedIn: 'root'
})
export class AdminGuardService {

  constructor(private router:Router ) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): boolean {

     const token=sessionStorage.getItem("token")
      if (token==null)  {
          alert('You are not an authorized admin ...');
          this.router.navigate(['/login']);
          return false;
      } 
      else{
        let decodedToken:Idecoded = jwt_decode(token);
          if(decodedToken.Role=="CANDIDATE"){
            alert('You are not an authorized admin ....');
            this.router.navigate(['/candidate/jobs']);
            return false;
          }
          else if(decodedToken.Role=="RECRUITER"){
            alert('You are not an authorized admin ....');
            this.router.navigate(['/recruiter/createdJobs']);
            return false;

          }
      }
      return true;
  }
}
