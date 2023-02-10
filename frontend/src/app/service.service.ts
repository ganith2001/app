import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http:HttpClient) { }

  createuser(email:String,password1:String){
    var res:any;
    const data={email:email,username:"Ganith",password:password1}
    this.http.post("http://localhost:8080/createuser",data, {
      headers: {
        Accept: 'application/json; charset=utf-8'
      }
    })
    .subscribe(response=>{
        res=response
        window.location.reload();
    })
    console.log(res)
  }
}
