import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { empty } from 'rxjs';
import Swal from 'sweetalert2';
import { PensionDetailService } from '../service/pension-detail.service';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-calculate-pension-amount',
  templateUrl: './calculate-pension-amount.component.html',
  styleUrls: ['./calculate-pension-amount.component.css']
})
export class CalculatePensionAmountComponent implements OnInit {

  calculatePensionForm:FormGroup;
  constructor(private service:PensionDetailService,private router:Router) { }

  ngOnInit(): void {
    this.calculatePensionForm = new FormGroup({
      'aadhaarNumber' : new FormControl(null,Validators.required)
      // 'name' : new FormControl(null,Validators.required),
      // 'dateOfBirth' : new FormControl(null,Validators.required),
      // 'pan' : new FormControl(null,Validators.required),
      // 'pensionType' : new FormControl(null,Validators.required)
    })
  }

  userDetail={
    aadhaarNumber:Number
  }
  pensionAmount;
  bankServiceCharge;


  onSubmit(){
    this.userDetail.aadhaarNumber = this.calculatePensionForm.get('aadhaarNumber').value;
    // this.userDetail.name = this.calculatePensionForm.get('name').value;
    // this.userDetail.dateOfBirth = this.calculatePensionForm.get('dateOfBirth').value;
    // this.userDetail.pan = this.calculatePensionForm.get('pan').value;
    // this.userDetail.pensionType = this.calculatePensionForm.get('pensionType').value;
    if(this.userDetail.aadhaarNumber!=null){
        this.service.getPensionDetail(this.userDetail).subscribe(
          (res:any)=>{
            console.log("submitted");
            //console.log(res);
            this.pensionAmount = res.pensionAmount;
            this.bankServiceCharge = res.bankServiceCharge;
            this.router.navigate(["calculatePension", this.pensionAmount,this.bankServiceCharge])},
          
          error =>{
            console.log("error");
          console.log(error);
          Swal.fire('Invalid!','Invalid user details','error'); 
          }
        )
    }else{
      console.log("empty fields");
      Swal.fire('Empty Feilds!','Please Enter user details');
    }
  }

  reset(){
    this.calculatePensionForm.reset();
  }

}
