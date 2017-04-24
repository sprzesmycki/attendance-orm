import {Routes, RouterModule} from "@angular/router";
import {ModuleWithProviders} from "@angular/core";
import {UsersListComponent} from "./users-list/users-list.component";
import {UserComponent} from "./user/user.component";
import {UserEditComponent} from "./user-edit/user-edit.component";
import {ActivitiesListComponent} from "./activities/activities-list.component";
import {ActivityEditComponent} from "./activities/activity-edit.component";

export const routes: Routes = [
  {path: 'useradd', component: UserEditComponent},
  {path: 'user/:id', component: UserComponent},
  {path: 'activityadd', component: UserEditComponent},
  {path: 'activity/:id', component: ActivityEditComponent},
  {path: 'activity', component: ActivitiesListComponent},
  //{path: 'attendance', component: UserEditComponent},
  {path: 'user', component: UsersListComponent},
  {path: '**', component: UsersListComponent},
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
