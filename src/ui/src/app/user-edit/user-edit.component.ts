import { Component, OnInit } from '@angular/core';
import { FormGroup } from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UsersService} from "../users/users-service.service";
import {UserFormService} from "../users/user-form.service";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css'],
  providers: [UsersService, UserFormService]
})
export class UserEditComponent implements OnInit {

  id: number;
  editForm: FormGroup = this.userFormService.getFormGroup('', '', '');

  constructor(private userFormService : UserFormService, private route : ActivatedRoute, private usersService : UsersService, private router : Router) {
    this.route.params.subscribe(params => {
      let id  = params['id'];
      if (id) {
        this.id = id;
        this.usersService.unique(id).subscribe(user => {
          this.editForm = this.userFormService.getFormGroup(user.firstName, user.lastName, user.email);
        });
      }
    });
  }

  ngOnInit() {
  }

  submit() {
    this.userFormService.saveForm(this.id, this.editForm).subscribe(r => {
      this.router.navigate(['/']);
    });
  }

}
