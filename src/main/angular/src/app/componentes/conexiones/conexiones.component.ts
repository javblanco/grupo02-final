import { Component, OnInit } from '@angular/core';
import { Conexion } from 'src/app/clases/conexion';
import { ConexionService } from './conexiones.service';

@Component({
  selector: 'app-conexiones',
  templateUrl: './conexiones.component.html',
  styleUrls: ['./conexiones.component.css']
})
export class ConexionesComponent implements OnInit {

  public conexiones: Conexion[] = [];

  constructor(
    private conexionService: ConexionService
  ) { }


   ngOnInit(): void {
    if (this.conexiones.length == 0) {
      this.conexionService.crearListaInicial().subscribe(
        () => {
          this._getConexiones();
        }
      );
    }
  }

<<<<<<< HEAD
   _getConexiones(): void {
=======
  _getConexiones(): void {
>>>>>>> 010243ba5f5a2f9794371a9d8d0e87a27cc2a48f
    this.conexionService.findConexiones().subscribe(
      (conexiones: Conexion[]) => {
        this.conexiones = conexiones
      }
    );
  }

<<<<<<< HEAD
=======
  public deleteConexion(conexion: Conexion): void {
    if(confirm("¿Está seguro de borrar la conexión " + conexion.id + "?")) {
      this.conexionService.deleteConexion(conexion).subscribe(
        () => {
          this._getConexiones();
        }
      );
    }
  }

>>>>>>> 010243ba5f5a2f9794371a9d8d0e87a27cc2a48f
}


