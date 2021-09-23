import { Component, OnInit } from '@angular/core';
import { Instancia } from 'src/app/clases/instancia';
import { InstanciaService } from './instancia.service';

@Component({
  selector: 'app-instancia',
  templateUrl: './instancia.component.html',
  styleUrls: ['./instancia.component.css']
})
export class InstanciaComponent implements OnInit {

  public instancia: Instancia[] = [];

  constructor(
    private instanciaService: InstanciaService
  ) { }


   ngOnInit(): void {
    if (this.instancia.length == 0) {
      this.instanciaService.crearListaInicial().subscribe(
        () => {
          this._getInstancias();
        }
      );
    }
  }

  _getInstancias(): void {
    this.instanciaService.findInstancias().subscribe(
      (instancias: Instancia[]) => {
        this.instancia = instancias
      }
    );
  }

  public deleteInstancia(instancia: Instancia): void {
    if(confirm("¿Está seguro de borrar la instancia " + instancia.id + "?")) {
      this.instanciaService.deleteInstancia(instancia).subscribe(
        () => {
          this._getInstancias();
        }
      );
    }
  }

}
