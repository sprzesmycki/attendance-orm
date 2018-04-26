import { Component, Inject, OnInit } from '@angular/core';
import { Router }      from '@angular/router';
import {UsersService} from "../users/users-service.service";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss'],
  providers: [UsersService]
})
export class UsersListComponent implements OnInit {

  users = [];

  user_edit: number = 0;

  query: string = '';

  constructor(private usersService: UsersService, private router: Router) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.usersService.get().subscribe((response) => {
      this.users = response;
    });
  }

  edit(id) {
    this.user_edit = id;
  }

  remove(id) {
    this.usersService.remove(id).subscribe((response) => {
      this.getUsers();
    }, (error) => {
      console.error(error);
    });
  }

  accept(userId, index) {
    this.usersService.update(userId, this.users[index]).subscribe((response) => {
      this.user_edit = 0;
      this.getUsers();
    }, (error) => {
      console.error(error);
    });
  }

  cancel() {
    this.user_edit = 0;
  }

  addStudent() {
    this.router.navigate(['/useradd']);
  }

}
