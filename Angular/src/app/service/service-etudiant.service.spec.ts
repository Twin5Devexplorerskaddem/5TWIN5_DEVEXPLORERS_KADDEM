import { TestBed } from '@angular/core/testing';

import { ServiceEtudiantService } from './service-etudiant.service';

describe('ServiceEtudiantService', () => {
  let service: ServiceEtudiantService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceEtudiantService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
