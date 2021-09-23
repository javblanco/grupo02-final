import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Flujo } from 'src/app/clases/flujo';
import { FlujoService } from '../flujo.service';

@Component({
  selector: 'app-flujo-add',
  templateUrl: './flujo-add.component.html',
  styleUrls: ['./flujo-add.component.css']
})
export class FlujoAddComponent implements OnInit {

  public flujo!: Flujo;
  private _isInsert: boolean = false;

  constructor(
    private _flujoService: FlujoService,
    private _router: Router,
    private _route: ActivatedRoute,
    private _location: Location
  ) { }

  public ngOnInit(): void {
    this.flujo = new Flujo();
    const id = this._route.snapshot.params['id'];
    if (id == null) {
      // Si el id es nulo, es un insert
      this.flujo = new Flujo();
      this._isInsert = true;
    } else {
      // Si el id no es nulo, es una modificacion
      this.flujo.id = Number(id);
      this._isInsert = false;
      this._getFlujo();
    }
  }

  private _getFlujo(): void {
    this._flujoService.getFlujo(this.flujo).subscribe(
      (flujo: Flujo) => {
        this.flujo = flujo
      }
    );
  }

  public save(): void {
    if (this._isInsert) {
      this._flujoService.insertFlujo(this.flujo).subscribe(
        () => {
          this.goBack();
        }
      );
    } else {
      this._flujoService.updateFlujo(this.flujo).subscribe(
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
