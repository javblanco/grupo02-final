import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Instancia } from '../clases/instancia';



@Injectable({ providedIn: 'root' })
export class InstanciaService {

  constructor(
   
    public httpClient: HttpClient
  ) { }

  public crearListaInicial(): Observable<any> {
    const url = 'http://192.168.56.1:8080/instancia/crearListaInicial';
    const body = null;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public findInstancias(): Observable<any> {
    const url = 'http://192.168.56.1:8080/instancia/lista';
    const body = null;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public getInstancias (instancia: Instancia): Observable<any> {
    const url = 'http://192.168.56.1:8080/instancia/detalleInstancia';
    const body = instancia;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.put(url, body, {headers: headers});

    
  }

  public insertInstancia (instancia: Instancia): Observable<any> {
    const url = 'http://192.168.56.1:8080/instancia/detalle';
    const body = instancia;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public updateInstancia (instancia: Instancia): Observable<any> {
    const url = 'http://192.168.56.1:8080/instancia/detalle';
    const body = instancia;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.put(url, body, {headers: headers});
  }

  

}