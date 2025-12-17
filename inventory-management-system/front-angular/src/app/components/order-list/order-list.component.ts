import { Component, OnInit, ChangeDetectorRef } from '@angular/core'; // <--- 1. IMPORTAR ESTO
import { ApiService } from '../../services/api-service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css'],
  standalone: false
})
export class OrderListComponent implements OnInit {
  orders: any[] = [];
  loading: boolean = true;

  // 2. INYECTAR ChangeDetectorRef EN EL CONSTRUCTOR
  constructor(private api: ApiService, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders() {
    this.loading = true;
    console.log('--- SOLICITANDO ÓRDENES ---');

    this.api.getOrders().subscribe({
      next: (data: any[]) => {
        console.log('DATOS RECIBIDOS:', data);

        // Mapeo seguro para evitar errores si faltan campos
        this.orders = data.map(o => ({
          id: o.id,
          status: o.status,
          // Buscamos totalAmount (camelCase) o total_amount (snake_case)
          totalAmount: o.totalAmount ?? o.total_amount ?? 0,
          createdAt: o.createdAt ?? o.created_at ?? new Date(),
          supplierName: o.supplierName ?? ('Prov ID: ' + o.supplierId),
          warehouseName: o.warehouseName ?? ('Bodega ID: ' + o.warehouseId)
        }));

        // Ordenar: Nuevas primero
        this.orders.sort((a, b) => b.id - a.id);

        console.log('LISTA PROCESADA (Longitud):', this.orders.length);

        this.loading = false;
        
        // 3. ¡DESPERTAR A ANGULAR A LA FUERZA!
        this.cdr.detectChanges(); 
      },
      error: (err) => {
        console.error('ERROR:', err);
        this.loading = false;
        this.cdr.detectChanges(); // También actualizar si hay error
      }
    });
  }

  approveOrder(id: number) {
    if(confirm('¿Aprobar orden #' + id + '?')) {
      this.api.updateOrderStatus(id, 'APPROVED').subscribe(() => {
        this.loadOrders();
      });
    }
  }

  receiveOrder(id: number) {
    if(confirm('¿Recibir mercadería de orden #' + id + '?')) {
      this.api.receiveOrder(id).subscribe(() => {
        alert('Stock Actualizado');
        this.loadOrders();
      });
    }
  }
}