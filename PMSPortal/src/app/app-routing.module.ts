import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { FormsModule } from "@angular/forms";
import { MatFormFieldModule } from '@angular/material/form-field';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthGuard } from './service/auth.guard';
import { PensionerDetailByAadhaarComponent } from './pensioner-detail-by-aadhaar/pensioner-detail-by-aadhaar.component';
import { CalculatePensionAmountComponent } from './calculate-pension-amount/calculate-pension-amount.component';

const routes: Routes = [
  {path:'', component : HomeComponent,pathMatch:'full'},
  {path:'home', component : HomeComponent,pathMatch:'full'},
  {path:'login', component : LoginComponent,pathMatch:'full'},
  {path:'dashboard', component : DashboardComponent,pathMatch:'full',canActivate:[AuthGuard]},
  {path:'pensionerDetail', component : PensionerDetailByAadhaarComponent ,pathMatch:'full',canActivate:[AuthGuard]},
  {path:'calculatePension', component : CalculatePensionAmountComponent ,pathMatch:'full',canActivate:[AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
