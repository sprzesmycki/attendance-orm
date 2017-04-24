import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ActivitiesService} from "./activities.service";
import {ActivityFormService} from "./activity-form.service";

@Component({
  selector: 'app-activity-edit',
  templateUrl: './activity-edit.component.html',
  styleUrls: ['./activity-edit.component.scss'],
  providers: [ActivityFormService, ActivitiesService]

})
export class ActivityEditComponent implements OnInit {

  id: number;
  editForm: FormGroup = this.activityFormService.getFormGroup('', null);

  constructor(private activityFormService : ActivityFormService, private route : ActivatedRoute, private activitiesService :  ActivitiesService, private router : Router) {
    this.route.params.subscribe(params => {
      let id  = params['id'];
      if (id) {
        this.id = id;
        this.activitiesService.unique(id).subscribe(activity => {
          this.editForm = this.activityFormService.getFormGroup(activity.name, activity.startDate, );
        });
      }
    });
  }

  ngOnInit() {
  }

  submit() {
    this.activityFormService.saveForm(this.id, this.editForm).subscribe(r => {
      this.router.navigate(['/activities']);
    });
  }

}
