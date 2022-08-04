import { HttpClient, HttpHandler } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ServiceService } from '../service/service.service';

import { CalculatePensionAmountComponent } from './calculate-pension-amount.component';

fdescribe('CalculatePensionAmountComponent', () => {
  let component: CalculatePensionAmountComponent;
  let fixture: ComponentFixture<CalculatePensionAmountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[RouterTestingModule],
      declarations: [ CalculatePensionAmountComponent ],
      providers:[ServiceService,HttpClient,HttpHandler]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CalculatePensionAmountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
