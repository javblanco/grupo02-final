import { Component, OnInit } from '@angular/core';
import { Flujo } from 'src/app/clases/flujo';
import { FlujoService } from './flujo.service';

@Component({
  selector: 'app-flujo',
  templateUrl: './flujo.component.html',
  styleUrls: ['./flujo.component.css']
})
export class FlujosComponent implements OnInit {

  public flujos: Flujo[] = [];

  constructor(
    private flujoService: FlujoService
  ) { }


  public ngOnInit(): void {
    this._getFlujos();
  }

  _getFlujos(): void {
    this.flujoService.findFlujos().subscribe(
      (flujos: Flujo[]) => {
        this.flujos = flujos
      }
    );
  }

  public deleteFlujo(flujo: Flujo): void {
    if(confirm("¿Está seguro de borrar el flujo " + flujo.id + "?")) {
      this.flujoService.deleteFlujo(flujo).subscribe(
        () => {
          this._getFlujos();
        }
      );
    }
  }

}