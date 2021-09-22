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
    const url = 'http://localhost:8080/conexiones/detalleConexion';
    const body = conexion;
    const headers = new HttpHeaders({'Content-type': 'application/json'});
    return this.httpClient.post(url, body, {headers: headers});
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

  

}