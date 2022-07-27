import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from '../service/service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginForm:FormGroup;

  constructor(private router:Router, private service: ServiceService ) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      'userid' : new FormControl(null,Validators.required),
      'uname' : new FormControl(null,Validators.required),
      'upassword' : new FormControl(null,Validators.required),
    });
  }
   userData = {
     userid:'',
     uname:'',
     upassword:''
   }
  

  

  onSubmit(){
    console.log("form submitted");
    
    this.userData.userid = this.loginForm.get('userid').value;
    this.userData.uname = this.loginForm.get('uname').value;
    this.userData.upassword = this.loginForm.get('upassword').value;
    
    if((this.userData.userid!='' && this.userData.uname!='' && this.userData.upassword!='')&&
    (this.userData.userid!=null && this.userData.uname!=null && this.userData.upassword!=null)){
        this.service.generateToken(this.userData).subscribe(
          (response: any)=>{
            console.log("success");
            console.log(response);
            console.log(response.authToken);
            console.log(response.userid);
            this.service.loginUser(response.authToken,response.userId);
            window.location.href="/dashboard";
          },
        error =>{
          console.log("error");
          console.log(error);
          Swal.fire('Invalid!','Wrong username or password','error'); 
          
        })
        }else{
          console.log("empty fields");
          Swal.fire('Empty Feilds!','Please Enter Credentials');
        }
     }


     reset(){
       this.loginForm.reset();
     }
  }


