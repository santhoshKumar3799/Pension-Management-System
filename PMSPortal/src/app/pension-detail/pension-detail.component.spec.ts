import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PensionDetailComponent } from './pension-detail.component';

describe('PensionDetailComponent', () => {
  let component: PensionDetailComponent;
  let fixture: ComponentFixture<PensionDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PensionDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PensionDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
