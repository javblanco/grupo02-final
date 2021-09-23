import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Conexion } from 'src/app/clases/conexion';

@Injectable({ providedIn: 'root' })
export class ConexionService {

  constructor(
    public httpClient: HttpClient
  ) { }

  public crearListaInicial(): Observable<any> {
    const url = 'http://localhost:8080/conexiones/crearListaInicial';
    const body = null;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public findConexiones(): Observable<any> {
    const url = 'http://localhost:8080/conexiones/lista';
    const body = null;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public getConexion (conexion: Conexion): Observable<any> {
<<<<<<< HEAD:src/main/angular/src/app/componentes/conexiones/conexiones.service.ts
    const url = 'http://localhost:8080/conexiones/detalleConexion';
    const body = conexion;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
=======
    const url = `http://localhost:8080/conexiones/detalle/${conexion.id}`;
    const options = { headers: new HttpHeaders({'Content-type': 'application/json'} )};
    return this.httpClient.get<Conexion>(url, options);
>>>>>>> 010243ba5f5a2f9794371a9d8d0e87a27cc2a48f:src/main/angular/src/app/conexiones/conexiones.service.ts
  }

  public insertConexion (conexion: Conexion): Observable<any> {
    const url = 'http://localhost:8080/conexiones/detalle';
    const body = conexion;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
  }

  public updateConexion (conexion: Conexion): Observable<any> {
    const url = 'http://localhost:8080/conexiones/detalle';
    const body = conexion;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.put(url, body, {headers: headers});
  }

  public deleteConexion (conexion: Conexion): Observable<any> {
    const url = `http://localhost:8080/conexiones/detalle/${conexion.id}`;
    const options = { headers: new HttpHeaders({'Content-type': 'application/json'} )};
    return this.httpClient.delete<Conexion>(url, options);
  }

}