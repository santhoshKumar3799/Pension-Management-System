import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PensionerDetailByAadhaarComponent } from './pensioner-detail-by-aadhaar.component';

describe('PensionerDetailByAadhaarComponent', () => {
  let component: PensionerDetailByAadhaarComponent;
  let fixture: ComponentFixture<PensionerDetailByAadhaarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PensionerDetailByAadhaarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PensionerDetailByAadhaarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
