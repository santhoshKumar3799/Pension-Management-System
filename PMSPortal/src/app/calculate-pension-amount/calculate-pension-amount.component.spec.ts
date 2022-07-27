import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalculatePensionAmountComponent } from './calculate-pension-amount.component';

describe('CalculatePensionAmountComponent', () => {
  let component: CalculatePensionAmountComponent;
  let fixture: ComponentFixture<CalculatePensionAmountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalculatePensionAmountComponent ]
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
