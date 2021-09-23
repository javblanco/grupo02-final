import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Conexion } from 'src/app/clases/conexion';
import { ConexionService } from '../conexiones.service';

@Component({
  selector: 'app-conexion-add',
  templateUrl: './conexion-add.component.html',
  styleUrls: ['./conexion-add.component.css']
})
export class ConexionAddComponent implements OnInit {

  public conexion!: Conexion;

  constructor(
    private _conexionService: ConexionService,
    private _route: ActivatedRoute,
    private _location: Location
  ) { }

  public ngOnInit(): void {
    this.conexion = new Conexion();
    const id = Number(this._route.snapshot.paramMap.get('id'));
    if (id == null) {
      // Si el id es nulo, es un insert
      this.conexion = new Conexion();
    } else {
      // SI el id no es nulo, es una modificacion
      this.conexion.id = id;
      this._getConexion();
    }
  }

  private _getConexion(): void {
    this._conexionService.getConexion(this.conexion).subscribe(
      (conexion: Conexion) => {
        this.conexion = conexion
      }
    );
  }

  public save(): void {
    this._conexionService.insertConexion(this.conexion).subscribe(
      () => {
        this.goBack();
      }
    );
  }

  public goBack(): void {
    this._location.back();
  }

}
