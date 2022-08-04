import { TestBed } from '@angular/core/testing';

import { PensionDetailService } from './pension-detail.service';

describe('PensionDetailService', () => {
  let service: PensionDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PensionDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
