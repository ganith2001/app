import { Injectable } from '@angular/core';

import { Router, CanActivate, ActivatedRouteSnapshot,RouterStateSnapshot } from '@angular/router';
import jwt_decode from 'jwt-decode';
import { Idecoded } from './types/decodestokentype';
 
@Injectable()
export class AuthGuardService implements CanActivate {
 
    constructor(private router:Router ) {
    }
 
    canActivate(route: ActivatedRouteSnapshot,
                state: RouterStateSnapshot): boolean {
 
       const token=sessionStorage.getItem("token");
        if (token!=null)  {
            alert('Please Logout ...');
            let decodedToken:Idecoded = jwt_decode(token);
            if(decodedToken.Role=="CANDIDATE"){     
              this.router.navigate(['/candidate/jobs']);
            }
            else if(decodedToken.Role=="RECRUITER"){
              this.router.navigate(['/recruiter/createdJobs']);
            }
            else if(decodedToken.Role=="ADMIN"){
              this.router.navigate(['/admin/addRecruiter']);
            }
            return false;
         
            
        } 
        return true;
    }
 
}
