import { Component } from '@angular/core';

import jwt_decode from 'jwt-decode';
import { Idecoded } from '../types/decodestokentype';
import { ServiceService } from '../service.service';

import { Router } from '@angular/router';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {

  public token:Idecoded={Role: "", sub: "", iat: 0, exp: 0};
  public recuiterDetails={name: '', email: '', emp_id: ''};
  constructor(private service:ServiceService,private router:Router) { }

  ngOnInit() {
    const token = sessionStorage.getItem("token");
 
    if(token!=null){
      
      let decodedToken:Idecoded = jwt_decode(token);
      this.token=decodedToken;

      if(this.token.Role=="RECRUITER"){
      this.service.getRecuiterDetails(this.token.sub).subscribe(res=>{
        
        this.recuiterDetails=res;
    
    })
    }
  }
  }

  onlogout(){
    sessionStorage.clear()
    
    this.router.navigate(['/login']);
    setTimeout(()=>{  window.location.reload() }, 100)
 
    
  }

}
