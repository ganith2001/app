import { Component } from '@angular/core';

import { ServiceService } from '../service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  public user={email:'',password1:'',password2:''};

  constructor(private service:ServiceService) {}

  onSignup(){
    if(this.user.password1==this.user.password2){
      this.service.createuser(this.user.email,this.user.password1);
    }
    
    
  }

}
