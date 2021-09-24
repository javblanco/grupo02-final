import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'grupo02-final';

  constructor(
    private appService: AppService
  ) { }

  public ngOnInit(): void {
    this.appService.rellenarTablas().subscribe(
      () => {

      }
    );
  }

}
