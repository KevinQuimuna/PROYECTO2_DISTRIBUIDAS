import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api-service';

@Component({
  selector: 'app-warehouse-create',
  templateUrl: './warehouse-create.component.html',
  standalone: false
})
export class WarehouseCreateComponent {
  warehouse = { name: '', location: '' };

  constructor(private api: ApiService, private router: Router) {}

  saveWarehouse() {
    this.api.createWarehouse(this.warehouse).subscribe(() => {
      alert('Bodega Creada');
      this.router.navigate(['/']); // Volver al inicio
    }, err => alert('Error al crear bodega'));
  }
}