import { Injectable } from '@angular/core';
import { ApiService } from "../api/api.service";

@Injectable()
export class ActivitiesService {

  private _basePath = 'activities/';

  constructor(private apiService : ApiService) { }

  get() {
    return this.apiService.get(this._basePath);
  }

  unique(id : number) {
    return this.apiService.get(this._basePath + id);
  }

  create(activity : any) {
    return this.apiService.post(this._basePath, activity);
  }

  update(id : number, activity : any) {
    return this.apiService.put(this._basePath + id, activity);
  }

  delete(id: number) {
    return this.apiService.delete(this._basePath + id);
  }

}
