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

   _getConexiones(): void {
    this.conexionService.findConexiones().subscribe(
      (conexiones: Conexion[]) => {
        this.conexiones = conexiones
      }
    );
  }

}


