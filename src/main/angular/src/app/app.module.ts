import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConexionesComponent } from './conexiones/conexiones.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './header/header.component';
import { ConexionAddComponent } from './conexion-add/conexion-add.component';
import { InstanciaComponent } from './instancia/instancia.component';
import { InstanciaAddComponent } from './instancia-add/instancia-add.component';
import { FlujoComponent } from './flujo/flujo.component';
import { Flujo } from './flujo.add/flujo.add.component';
import { FooterComponent } from './footer/footer.component';



@NgModule({
  declarations: [
    AppComponent,
    ConexionesComponent,
    HeaderComponent,
    ConexionAddComponent,
    InstanciaComponent,
    InstanciaAddComponent,
    FlujoComponent,
    Flujo.AddComponent,
    FooterComponent,
 
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
