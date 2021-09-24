import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Instancia } from 'src/app/clases/instancia';



@Injectable({ providedIn: 'root' })
export class InstanciaService {

  constructor(
   
    public httpClient: HttpClient
  ) { }

  public crearListaInicial(): Observable<any> {
    const url = 'http://localhost:8080/instancias/crearListaInicial';
    const body = null;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public findInstancias(): Observable<any> {
    const url = 'http://localhost:8080/instancias/lista';
    const body = null;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public getInstancia (instancia: Instancia): Observable<any> {
    const url = `http://localhost:8080/instancias/detalle/${instancia.id}`;
    const options = { headers: new HttpHeaders({'Content-type': 'application/json'} )};
    return this.httpClient.get<Instancia>(url, options);
  }

  public insertInstancia (instancia: Instancia): Observable<any> {
    const url = 'http://localhost:8080/instancias/detalle';
    const body = instancia;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public updateInstancia (instancia: Instancia): Observable<any> {
    const url = 'http://localhost:8080/instancias/detalle';
    const body = instancia;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.put(url, body, {headers: headers});
  }

  public deleteInstancia (instancia: Instancia): Observable<any> {
    const url = `http://localhost:8080/instancias/detalle/${instancia.id}`;
    const options = { headers: new HttpHeaders({'Content-type': 'application/json'} )};
    return this.httpClient.delete<Instancia>(url, options);
  }

}