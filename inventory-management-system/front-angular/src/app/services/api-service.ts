import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  // Ajusta los puertos según tu docker-compose
  private productsUrl = 'http://localhost:8081/api/products';
  private suppliersUrl = 'http://localhost:8082/api/suppliers';
  private inventoryUrl = 'http://localhost:8083/api';
  private ordersUrl = 'http://localhost:8084/api/orders';

  constructor(private http: HttpClient) { }

  // Productos
  getProducts(): Observable<any[]> { return this.http.get<any[]>(this.productsUrl); }
  createProduct(product: any): Observable<any> { return this.http.post(this.productsUrl, product); }

  // Proveedores
  getSuppliers(): Observable<any[]> { return this.http.get<any[]>(this.suppliersUrl); }
  createSupplier(supplier: any): Observable<any> { return this.http.post(this.suppliersUrl, supplier); }

  // Bodegas (Inventory Service)
  getWarehouses(): Observable<any[]> { return this.http.get<any[]>(`${this.inventoryUrl}/warehouses`); }
  createWarehouse(warehouse: any): Observable<any> { return this.http.post(`${this.inventoryUrl}/warehouses`, warehouse); }

  // Órdenes
  getOrders(): Observable<any[]> { return this.http.get<any[]>(this.ordersUrl); }
  createOrder(order: any): Observable<any> { return this.http.post(this.ordersUrl, order); }
  updateOrderStatus(id: number, status: string): Observable<any> { 
    return this.http.patch(`${this.ordersUrl}/${id}/status?status=${status}`, {}); 
  }
  receiveOrder(id: number): Observable<any> {
      return this.http.post(`${this.ordersUrl}/${id}/receive`, {});
  }
}