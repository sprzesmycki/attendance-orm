import { Injectable } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivitiesService} from "./activities.service";

@Injectable()
export class ActivityFormService {

  constructor(private formBuilder : FormBuilder, private activitiesService : ActivitiesService) { }

  getFormGroup(name: string, startDate : number, ) : FormGroup {
    return this.formBuilder.group({
      name: [name, Validators.required],
      startDate: [startDate, Validators.required]
    });
  }

  saveForm(id : number, formGroup : FormGroup) {
    if (id) {
      return this.activitiesService.update(id, formGroup.value);
    } else {
      return this.activitiesService.create(formGroup.value);
    }
  }

}
