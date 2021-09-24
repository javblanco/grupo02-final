import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AppService {

  constructor(
    public httpClient: HttpClient
  ) { }

  public rellenarTablas(): Observable<any> {
    const url = 'http://localhost:8080/inicio/rellenarTablas';
    const body = null;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

}
