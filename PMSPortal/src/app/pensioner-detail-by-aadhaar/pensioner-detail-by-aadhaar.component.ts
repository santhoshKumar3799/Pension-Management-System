import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-pensioner-detail-by-aadhaar',
  templateUrl: './pensioner-detail-by-aadhaar.component.html',
  styleUrls: ['./pensioner-detail-by-aadhaar.component.css']
})
export class PensionerDetailByAadhaarComponent implements OnInit {
  aadhaarForm:FormGroup;
  constructor() { }

  ngOnInit(): void {
    this.aadhaarForm = new FormGroup({
      'aadhaar' : new FormControl(null,Validators.required)
    })
  }

  onSubmit(){
    console.log('submitted');
  }

  reset(){
    this.aadhaarForm.reset();
  }
}
