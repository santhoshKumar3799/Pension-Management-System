import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pension-detail',
  templateUrl: './pension-detail.component.html',
  styleUrls: ['./pension-detail.component.css']
})
export class PensionDetailComponent implements OnInit {

  constructor(private router:ActivatedRoute) { }
  pensionDetail = {
    pensionAmount:'',
    bankServiceCharge:''
  }
  ngOnInit(): void {
     this.pensionDetail.pensionAmount = this.router.snapshot.paramMap.get('pensionAmount');
     this.pensionDetail.bankServiceCharge = this.router.snapshot.paramMap.get('bankServiceCharge');
    console.log(this.pensionDetail);
  }

}
