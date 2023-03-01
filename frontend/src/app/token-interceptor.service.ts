import { Injectable } from '@angular/core';

import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class TokenInterceptorService implements HttpInterceptor {

  constructor() { }

  intercept(req:HttpRequest<any>, next:HttpHandler):Observable<HttpEvent<any>> {
        let token;
        if(sessionStorage.getItem("token")!=null){
           token=sessionStorage.getItem("token");
        }
        else{
          token="";
        }
        
       let tokenizesReq = req.clone({
        setHeaders : {
          Authorization:'Bearer '+token
        }
       })
      return next.handle(tokenizesReq)
  }
}
