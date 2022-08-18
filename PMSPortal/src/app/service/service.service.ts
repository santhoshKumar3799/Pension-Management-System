import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http:HttpClient) { }

    // set your base URL here
    //baseUrl: string = 'http:///localhost:8081'
    //`${this.baseUrl}/login`

  generateToken(userData){
    return this.http.post<any>("http://44.205.9.132:8000/login", userData);
  }

  //login user
  loginUser(token: string,userId : string){
    localStorage.setItem("token",token);
    localStorage.setItem("userId",userId);
    return true;
  }


  //is user logged in
  isLoggedIn()
  {
      let token=localStorage.getItem('token');
      if(token==undefined||token==''||token==null)
      {
        return false;
      }else{

        return true;
      }
  }


  //logout the user
  logout()
  {
    localStorage.removeItem('token');
    return true;
  }


  //get the token
  getToken()
  {
    return localStorage.getItem('token');
  }

}
