import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import { Observable } from "rxjs/Observable";
import 'rxjs/Rx';

@Injectable()
export class ApiService {

private _url : string = '/api/';

  constructor(private http : Http) { }

  post(path : string, payload: any): Observable<any> {
    return this.http.post(this._url + path, payload, { withCredentials : true }).map(response => this.mapResponse(response));
  }

  put(path : string, payload: any): Observable<any> {
    return this.http.put(this._url + path, payload, { withCredentials : true }).map(response => this.mapResponse(response));
  }

  get(path : string): Observable<any> {
    return this.http.get(this._url + path, { withCredentials : true }).map(response => this.mapResponse(response));
  }

  delete(path: string): Observable<any> {
    return this.http.delete(this._url + path, {withCredentials: true}).map(response => this.mapResponse(response));
  }

  private mapResponse(response : Response) {
      if (response.status === 200 && response.text()) {
        return response.json();
      }
  }

}
