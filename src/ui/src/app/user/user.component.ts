import {Component, OnInit} from "@angular/core";
import {UsersService} from "../users/users-service.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  providers: [UsersService]
})
export class UserComponent implements OnInit {

  id: number;
  user: any = {};

  constructor(private usersService : UsersService, private route : ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      let id = params['id'];
      this.usersService.unique(id).subscribe(r => {
        this.user = r;
        this.id = id;
      });
    });
  }

  updateActivity(present: boolean, activity: any) {
    activity.present = present;
    this.usersService.updateActivity(this.id, activity).subscribe();
  }

}
