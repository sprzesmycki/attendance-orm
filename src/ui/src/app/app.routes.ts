import {Routes, RouterModule} from "@angular/router";
import {ModuleWithProviders} from "@angular/core";
import {UsersListComponent} from "./users-list/users-list.component";
import {UserComponent} from "./user/user.component";
import {UserEditComponent} from "./user-edit/user-edit.component";

export const routes: Routes = [
  {path: 'useradd', component: UserEditComponent},
  {path: 'user/:id', component: UserComponent},
  {path: '**', component: UsersListComponent},
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
