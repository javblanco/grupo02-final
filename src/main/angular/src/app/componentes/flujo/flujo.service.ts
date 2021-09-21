import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Flujo } from 'src/app/clases/flujo';



@Injectable({ providedIn: 'root' })
export class FlujoService {

  constructor(
   
    public httpClient: HttpClient
  ) { }

  public crearListaInicial(): Observable<any> {
    const url = 'http://192.168.56.1:8080/flujo/crearListaInicial';
    const body = null;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public findFlujos(): Observable<any> {
    const url = 'http://192.168.56.1:8080/flujo/lista';
    const body = null;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public getFlujo (flujo: Flujo): Observable<any> {
    const url = 'http://192.168.56.1:8080/flujo/detalleFlujo';
    const body = flujo;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.put(url, body, {headers: headers});

    
  }

  public insertFlujo (flujo: Flujo): Observable<any> {
    const url = 'http://192.168.56.1:8080/flujo/detalle';
    const body = flujo;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public updateFlujo (flujo: Flujo): Observable<any> {
    const url = 'http://192.168.56.1:8080/flujo/detalle';
    const body = flujo;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.put(url, body, {headers: headers});
  }

  

}
