import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PensionDetailService {
  constructor(private http: HttpClient) {}

  getPensionerDetail(aadhaarNumber) {
    const params = new HttpParams().set('aadhaarNumber', aadhaarNumber);
    return this.http.get<any>(
      'http://44.205.9.132:8100/PensionerDetailByAadhaar',{params}
    );
  }


  getPensionDetail(userDetail){
    return this.http.post<any>('http://44.205.9.132:8200/ProcessPension',userDetail);
  }
}
