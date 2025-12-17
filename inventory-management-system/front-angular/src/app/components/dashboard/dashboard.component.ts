import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  standalone: false  // <--- ESTA ES LA CLAVE. Antes estaba en true.
})
export class DashboardComponent {}