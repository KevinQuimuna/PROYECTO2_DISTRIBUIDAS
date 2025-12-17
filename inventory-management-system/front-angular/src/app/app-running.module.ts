import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// CORRIGE LAS RUTAS ASÍ (quita el .component del final del string):
import { DashboardComponent } from './components/dashboard/dashboard.component'; 
import { ProductListComponent } from './components/product-list/product-list.component'; // Este dijiste que sí funcionaba
import { SupplierListComponent } from './components/supplier-list/supplier-list.component';
import { OrderCreateComponent } from './components/order-create/order-create.component';
import { OrderListComponent } from './components/order-list/order-list.component';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'suppliers', component: SupplierListComponent },
  { path: 'create-order', component: OrderCreateComponent },
  { path: 'orders', component: OrderListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }