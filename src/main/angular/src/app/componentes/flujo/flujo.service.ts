import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Flujo } from 'src/app/clases/flujo';

@Injectable({ providedIn: 'root' })
export class FlujoService {

  constructor(
    public httpClient: HttpClient
  ) { }

  public findFlujos(): Observable<any> {
    const url = 'http://localhost:8080/flujos/lista';
    const body = null;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public getFlujo (flujo: Flujo): Observable<any> {
    const url = `http://localhost:8080/flujos/detalle/${flujo.id}`;
    const options = { headers: new HttpHeaders({'Content-type': 'application/json'} )};
    return this.httpClient.get<Flujo>(url, options);
  }

  public insertFlujo (flujo: Flujo): Observable<any> {
    const url = 'http://localhost:8080/flujos/detalle';
    const body = flujo;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public updateFlujo (flujo: Flujo): Observable<any> {
    const url = 'http://localhost:8080/flujos/detalle';
    const body = flujo;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.put(url, body, {headers: headers});
  }

  public deleteFlujo (flujo: Flujo): Observable<any> {
    const url = `http://localhost:8080/flujos/detalle/${flujo.id}`;
    const options = { headers: new HttpHeaders({'Content-type': 'application/json'} )};
    return this.httpClient.delete<Flujo>(url, options);
  }

}
