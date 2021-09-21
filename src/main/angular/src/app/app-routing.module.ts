import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConexionesComponent } from './conexiones/conexiones.component';


const routes: Routes = [
{ path: 'conexiones', component: ConexionesComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
