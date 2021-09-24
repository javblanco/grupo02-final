import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConexionAddComponent } from './conexiones/conexion-add/conexion-add.component';
import { ConexionesComponent } from './conexiones/conexiones.component';
import { FlujoAddComponent } from './flujo/flujo-add/flujo-add.component';
import { FlujosComponent } from './flujo/flujo.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { InstanciaAddComponent } from './instancia/instancia-add/instancia-add.component';
import { InstanciaComponent } from './instancia/instancia.component';



@NgModule({
  declarations: [
    AppComponent,
    ConexionesComponent,
    HeaderComponent,
    ConexionAddComponent,
    InstanciaComponent,
    InstanciaAddComponent,
    FlujosComponent,
    FlujoAddComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
