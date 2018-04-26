import { Component, OnInit } from '@angular/core';
import {UsersService} from "../users/users-service.service";
import { ActivitiesService } from "../activities/activities.service";

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.scss'],
  providers: [UsersService, ActivitiesService]
})
export class AttendanceComponent implements OnInit {

  users = [];
  activities = [];

  constructor(private usersService : UsersService, private activitiesService : ActivitiesService) { }

  ngOnInit() {
    this.reloadUsers();

    this.activitiesService.get().subscribe(r => {
      this.activities = r;
    });
  }

  reloadUsers() {
    this.usersService.get().subscribe(response => {
      this.users = response;
    });
  }

  userWasPresent(user, activity) {
    let attendance = user.activities.find(a => a.activityId === activity.id);
    return !!attendance ? attendance.present : false;
  }

  togglePresence(user, activity, event) {
    this.usersService.updateActivity(
      user.id,
      {activityId: activity.id, present: !this.userWasPresent(user, activity)}
    ).subscribe(this.reloadUsers.bind(this),
    function (error) { /* could show some kind of alert here */});

    event.preventDefault();
  }
}
