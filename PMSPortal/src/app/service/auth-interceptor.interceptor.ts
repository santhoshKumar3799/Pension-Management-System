import { Injectable } from '@angular/core';
import {
  HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest 
} from '@angular/common/http';
import { catchError, Observable, retry, throwError } from 'rxjs';
import { ServiceService } from './service.service';

@Injectable()
export class AuthInterceptorInterceptor implements HttpInterceptor {

  constructor(private service : ServiceService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.service.getToken()}`
      }
    });

    console.log("inside interceptor");
    return next.handle(request)
          .pipe(retry(1),
         catchError((error:HttpErrorResponse)=>
           {
              
              return throwError(console.error);
           })
     )

  }


}
