import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Instancia } from 'src/app/clases/instancia';
import { InstanciaService } from '../instancia.service';

@Component({
  selector: 'app-instancia-add',
  templateUrl: './instancia-add.component.html',
  styleUrls: ['./instancia-add.component.css']
})
export class InstanciaAddComponent implements OnInit {

  public instancia!: Instancia;
  private _isInsert: boolean = false;

  constructor(
    private _instanciaService: InstanciaService,
    private _router: Router,
    private _route: ActivatedRoute,
    private _location: Location
  ) { }

  public ngOnInit(): void {
    this.instancia = new Instancia();
    const id = this._route.snapshot.params['id'];
    if (id == null) {
      // Si el id es nulo, es un insert
      this.instancia = new Instancia();
      this._isInsert = true;
    } else {
      // Si el id no es nulo, es una modificacion
      this.instancia.id = Number(id);
      this._isInsert = false;
      this._getInstancia();
    }
  }

  private _getInstancia(): void {
    this._instanciaService.getInstancia(this.instancia).subscribe(
      (instancia: Instancia) => {
        this.instancia = instancia
      }
    );
  }

  public save(): void {
    if (this._isInsert) {
      this._instanciaService.insertInstancia(this.instancia).subscribe(
        () => {
          this.goBack();
        }
      );
    } else {
      this._instanciaService.updateInstancia(this.instancia).subscribe(
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
