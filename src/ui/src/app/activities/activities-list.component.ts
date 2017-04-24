import { Component, OnInit } from '@angular/core';
import { Router }      from '@angular/router';
import { ActivitiesService } from "./activities.service";

@Component({
  selector: 'app-activities-list',
  templateUrl: './activities-list.component.html',
  styleUrls: ['./activities-list.component.scss'],
  providers: [ActivitiesService]
})
export class ActivitiesListComponent implements OnInit {

  activities = [];

  activity_edit: number = 0;

  query: string ='';

  constructor(private activitiesService : ActivitiesService, private router: Router) { }

  ngOnInit() {
    this.getActivities();
  }

  getActivities() {
    this.activitiesService.get().subscribe(response => {
      this.activities = response;
    });
  }

  edit(id) {
    this.activity_edit = id;
  }

  remove(id) {
    this.activitiesService.delete(id).subscribe((response) => {
      this.getActivities();
    }, (error) => {
      console.error(error);
    });
  }

  accept(activityId, index) {
    this.activitiesService.update(activityId, this.activities[index]).subscribe((response) => {
      this.activity_edit = 0;
      this.getActivities();
    }, (error) => {
      console.error(error);
    });
  }

  cancel() {
    this.activity_edit = 0;
  }

  addActivity() {
    this.router.navigate(['/activityadd']);
  }

}
