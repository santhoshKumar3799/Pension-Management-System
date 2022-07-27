import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule ,HTTP_INTERCEPTORS} from '@angular/common/http';
import { ServiceService } from './service/service.service';
import { PensionerDetailByAadhaarComponent } from './pensioner-detail-by-aadhaar/pensioner-detail-by-aadhaar.component';
import { CalculatePensionAmountComponent } from './calculate-pension-amount/calculate-pension-amount.component';



@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginComponent,
    HomeComponent,
    DashboardComponent,
    PensionerDetailByAadhaarComponent,
    CalculatePensionAmountComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
