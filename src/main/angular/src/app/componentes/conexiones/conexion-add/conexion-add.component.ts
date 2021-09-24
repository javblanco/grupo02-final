import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Conexion } from 'src/app/clases/conexion';
import { ConexionService } from '../conexiones.service';

@Component({
  selector: 'app-conexion-add',
  templateUrl: './conexion-add.component.html',
  styleUrls: ['./conexion-add.component.css']
})
export class ConexionAddComponent implements OnInit {

  public conexion!: Conexion;
  private _isInsert: boolean = false;

  constructor(
    private _conexionService: ConexionService,
    private _router: Router,
    private _route: ActivatedRoute,
    private _location: Location,
  ) { }

  public ngOnInit(): void {
    this.conexion = new Conexion();
    const id = this._route.snapshot.params['id'];
    if (id == null) {
      // Si el id es nulo, es un insert
      this.conexion = new Conexion();
      this._isInsert = true;
    } else {
      // Si el id no es nulo, es una modificacion
      this.conexion.id = Number(id);
      this._isInsert = false;
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
    if (this._isInsert) {
      if(confirm("¿Está seguro de crear la conexión ?")) {
        this._conexionService.insertConexion(this.conexion).subscribe(
          () => {
            
            this.goBack();
          }
        );
      }
      
    } else {
      this._conexionService.updateConexion(this.conexion).subscribe(
        () => {
          this.goBack();
        }
      );
    }
  }

  public goBack(): void {
    this._router.navigate(['../'+ (!this._isInsert ? '../' : '')], { relativeTo: this._route});
  }

}
