import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api-service';

@Component({
  selector: 'app-supplier-list',
  templateUrl: './supplier-list.component.html',
  styleUrls: ['./supplier-list.component.css'],
  standalone: false
})
export class SupplierListComponent implements OnInit {
  suppliers: any[] = [];
  filteredSuppliers: any[] = []; // Fix visualización
  newSupplier = { name: '', email: '', phoneNumber: '', address: '', active: true };

  constructor(private api: ApiService) { }

  ngOnInit(): void { this.loadSuppliers(); }

  loadSuppliers() {
    this.api.getSuppliers().subscribe(data => {
      this.suppliers = data;
      this.filteredSuppliers = data; // Inicializar
    });
  }

  saveSupplier() {
    this.api.createSupplier(this.newSupplier).subscribe(() => {
      this.loadSuppliers();
      this.newSupplier = { name: '', email: '', phoneNumber: '', address: '', active: true };
    });
  }
}