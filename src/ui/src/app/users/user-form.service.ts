import { Injectable } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UsersService} from "./users-service.service";

@Injectable()
export class UserFormService {

  constructor(private formBuilder : FormBuilder, private usersService : UsersService) { }

  getFormGroup(firstName: string, lastName : string, email: string) : FormGroup {
    return this.formBuilder.group({
      firstName: [firstName, Validators.required],
      lastName: [lastName, Validators.required],
      email: [email, Validators.required]
    });
  }

  saveForm(id : number, formGroup : FormGroup) {
    if (id) {
      return this.usersService.update(id, formGroup.value);
    } else {
      formGroup.value.password = '123456';
      return this.usersService.create(formGroup.value);
    }
  }

}
