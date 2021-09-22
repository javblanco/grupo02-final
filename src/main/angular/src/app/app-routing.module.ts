import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConexionesComponent } from './conexiones/conexiones.component';
import { FlujoComponent } from './flujo/flujo.component';
import { InstanciaComponent } from './instancia/instancia.component';
import { InstanciaAddComponent } from './instancia-add/instancia-add.component';
import { FlujoAddComponent } from './flujo-add/flujo-add.component';
import { ConexionAddComponent } from './conexion-add/conexion-add.component';


const routes: Routes = [
{ path: 'conexiones', component: ConexionesComponent},
 { path: 'flujo', component: FlujoComponent},
 {path: 'instancia', component: InstanciaComponent},
 {path: 'flujo-add', component: FlujoAddComponent},
 {path: 'instancia-add', component: InstanciaAddComponent},
 {path: 'conexion-add', component: ConexionAddComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
