/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ActivityFormService } from './activity-form.service';

describe('ActivityFormService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ActivityFormService]
    });
  });

  it('should ...', inject([ActivityFormService], (service: ActivityFormService) => {
    expect(service).toBeTruthy();
  }));
});
