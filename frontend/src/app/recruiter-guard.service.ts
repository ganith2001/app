import { Injectable } from '@angular/core';

import { Router, CanActivate, ActivatedRouteSnapshot,RouterStateSnapshot } from '@angular/router';
import jwt_decode from 'jwt-decode';
import { Idecoded } from './types/decodestokentype';

@Injectable()
export class RecruiterGuardService implements CanActivate {
  constructor(private router:Router ) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): boolean {

     const token=sessionStorage.getItem("token")
      if (token==null)  {
          alert('You are not an authorized employee ...');
          this.router.navigate(['/login']);
          return false;
      } 
      else{
        let decodedToken:Idecoded = jwt_decode(token);
          if(decodedToken.Role!="RECRUITER"){
            alert('You are not an authorized employee ... ...');
            this.router.navigate(['/candidate/jobs']);
            return false;
          }
      }
      return true;
  }
}
