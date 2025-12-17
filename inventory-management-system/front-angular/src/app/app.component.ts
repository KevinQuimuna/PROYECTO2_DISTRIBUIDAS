import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: false // <--- IMPORTANTE
})
export class AppComponent {
  title = 'inventory-frontend';
}