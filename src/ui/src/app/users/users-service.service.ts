import { Injectable } from '@angular/core';
import { ApiService } from "../api/api.service";

@Injectable()
export class UsersService {

  constructor(private apiService : ApiService) { }

  private _basePath = 'users/';
  private _activities_path = 'activities/';

  get() {
    return this.apiService.get(this._basePath + '?' + 'expand');
  }

  remove(id) {
    return this.apiService.delete(this._basePath + id);
  }

  unique(id : number) {
    return this.apiService.get(this._basePath + id);
  }

  create(user : any) {
    return this.apiService.post(this._basePath, user);
  }

  update(id : number, user : any) {
    return this.apiService.put(this._basePath + id, user);
  }

  getActivities(id : number) {
    return this.apiService.get(this._basePath + id + '/' + this._activities_path);
  }

  updateActivity(id : number, activity : any) {
    return this.apiService.put(this._basePath + id + '/' + this._activities_path + activity.activityId, activity);
  }

}
