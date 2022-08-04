import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public loggedIn=false;
  constructor(private service:ServiceService,private router:Router) { }

  ngOnInit(): void {
    this.loggedIn=this.service.isLoggedIn();
  }

  logoutUser(){
    this.service.logout();
    this.ngOnInit();
    this.router.navigate(['/']);
  }

}
