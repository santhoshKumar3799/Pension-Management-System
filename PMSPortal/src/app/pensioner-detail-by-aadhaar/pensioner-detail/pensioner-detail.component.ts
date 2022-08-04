import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PensionDetailService } from 'src/app/service/pension-detail.service';
import { ServiceService } from 'src/app/service/service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-pensioner-detail',
  templateUrl: './pensioner-detail.component.html',
  styleUrls: ['./pensioner-detail.component.css']
})
export class PensionerDetailComponent implements OnInit {

  
  constructor(private router:ActivatedRoute, private pensionService : PensionDetailService,
    private service: ServiceService ) { }
  aadhaarNumber:Number;
  token:string;
  userDetails = {
    name:'',
    dateOfBirth:'',
    pan:'',
    salary:Number,
    allowance:Number,
    pensionType:'',
    aadhaarNumber:Number,
    bank : {
      bankName:'',
      accountNumber:Number,
      bankType:''
    }


  }
  ngOnInit(): void {
    this.aadhaarNumber = this.router.snapshot.params['aadhaar'];
    console.log(this.aadhaarNumber);
    //this.token = this.service.getToken();
    //console.log(this.token);
    if(this.aadhaarNumber!=null ){
      this.pensionService.getPensionerDetail(this.aadhaarNumber).subscribe(
        (response:any)=>{
          //console.log(response);
          this.userDetails.name = response.name;
          // console.log(this.userDetails.name)
          this.userDetails.dateOfBirth =response.dateOfBirth;
          this.userDetails.pan = response.pan;
          this.userDetails.salary = response.salary;
          //console.log(this.userDetails.salary);
          this.userDetails.allowance = response.allowance;
          this.userDetails.pensionType = response.pensionType;
          this.userDetails.aadhaarNumber=response.aadhaarNumber;
          this.userDetails.bank.bankName = response.bank.bankName;
          this.userDetails.bank.accountNumber = response.bank.accountNumber;
          this.userDetails.bank.bankType = response.bank.bankType;

          console.log(this.userDetails);
        },error =>{
          console.log("error");
          console.log(error);
          Swal.fire('Invalid!','Wrong aadhaar number','error');
          setTimeout(()=> window.location.href="/pensionerDetail" , 2000)
         
          
        }
      )
    }
  }


  
}
