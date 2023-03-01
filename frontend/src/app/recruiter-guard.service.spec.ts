import { TestBed } from '@angular/core/testing';

import { RecruiterGuardService } from './recruiter-guard.service';

describe('RecruiterGuardService', () => {
  let service: RecruiterGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecruiterGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
