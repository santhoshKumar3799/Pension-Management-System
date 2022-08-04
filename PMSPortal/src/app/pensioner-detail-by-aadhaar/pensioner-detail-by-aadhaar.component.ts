import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-pensioner-detail-by-aadhaar',
  templateUrl: './pensioner-detail-by-aadhaar.component.html',
  styleUrls: ['./pensioner-detail-by-aadhaar.component.css']
})
export class PensionerDetailByAadhaarComponent implements OnInit {
  aadhaarForm:FormGroup;

  aadhaarNum ;
  constructor(private router:Router) { }

  ngOnInit(): void {
    this.aadhaarForm = new FormGroup({
      'aadhaar' : new FormControl(null,Validators.required)
    })
  }

  onSubmit(){
    console.log('submitted');

    this.aadhaarNum = this.aadhaarForm.get('aadhaar').value;
    console.log(this.aadhaarNum);
    this.router.navigate(["pensionerDetailInfo", this.aadhaarNum]);
  }

  reset(){
    this.aadhaarForm.reset();
  }
}
