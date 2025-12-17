import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api-service';

@Component({
  selector: 'app-order-create',
  templateUrl: './order-create.component.html',
  styleUrls: ['./order-create.component.css'],
  standalone: false
})
export class OrderCreateComponent implements OnInit {
  suppliers: any[] = [];
  warehouses: any[] = [];
  products: any[] = [];
  
  selectedSupplierId: number = 0;
  selectedWarehouseId: number = 0;
  selectedProductId: number = 0;
  quantity: number = 1;
  notes: string = '';
  
  items: any[] = [];

  constructor(private api: ApiService, private router: Router) { }

  ngOnInit(): void {
    // Cargamos los catálogos para llenar los Selects
    this.api.getSuppliers().subscribe(d => this.suppliers = d);
    this.api.getWarehouses().subscribe(d => this.warehouses = d);
    this.api.getProducts().subscribe(d => this.products = d);
  }

  addItem() {
    // Buscamos el producto completo para sacar precio y SKU
    const product = this.products.find(p => p.id === this.selectedProductId);
    
    if(product && this.quantity > 0) {
      // Verificamos si ya existe para sumarlo (opcional) o agregamos nuevo
      this.items.push({
        productId: this.selectedProductId,
        quantity: this.quantity,
        sku: product.sku,
        unitPrice: product.price
      });
      // Resetear campos de item
      this.quantity = 1;
      this.selectedProductId = 0;
    }
  }

  removeItem(index: number) {
    this.items.splice(index, 1);
  }

  calculateTotal(): number {
    return this.items.reduce((acc, item) => acc + (item.quantity * item.unitPrice), 0);
  }

  // Ayuda visual para el HTML
  getProductName(id: number): string {
    const p = this.products.find(x => x.id === id);
    return p ? p.name : 'Desconocido';
  }

  createOrder() {
    if (this.selectedSupplierId === 0 || this.selectedWarehouseId === 0 || this.items.length === 0) {
      alert('Faltan datos (Proveedor, Bodega o Productos)');
      return;
    }

    const order = {
      supplierId: this.selectedSupplierId,
      warehouseId: this.selectedWarehouseId,
      items: this.items,
      notes: this.notes
    };

    this.api.createOrder(order).subscribe({
      next: () => {
        alert('Orden Generada Exitosamente');
        this.router.navigate(['/orders']);
      },
      error: (err) => {
        console.error(err);
        alert('Error al guardar la orden. Revisa la consola.');
      }
    });
  }
}