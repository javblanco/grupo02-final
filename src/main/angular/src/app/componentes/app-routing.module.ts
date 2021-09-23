import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConexionAddComponent } from './conexiones/conexion-add/conexion-add.component';
import { ConexionesComponent } from './conexiones/conexiones.component';
import { FlujoAddComponent } from './flujo/flujo-add/flujo-add.component';
import { FlujosComponent } from './flujo/flujo.component';
import { InstanciaAddComponent } from './instancia/instancia-add/instancia-add.component';
import { InstanciaComponent } from './instancia/instancia.component';


const routes: Routes = [
  { path: 'conexiones', component: ConexionesComponent},
  { path: 'conexiones/alta', component: ConexionAddComponent},
  { path: 'conexiones/detalle/:id', component: ConexionAddComponent},
  { path: 'flujo', component: FlujosComponent},
  { path: 'flujos/alta', component: FlujoAddComponent},
  { path: 'flujos/detalle/:id', component: FlujoAddComponent},
  { path: 'instancia', component: InstanciaComponent},
  { path: 'instancias/alta', component: InstanciaAddComponent},
  { path: 'instancias/detalle/:id', component: InstanciaAddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
