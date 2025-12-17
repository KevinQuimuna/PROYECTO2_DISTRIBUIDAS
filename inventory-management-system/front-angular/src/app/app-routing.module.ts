import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { SupplierListComponent } from './components/supplier-list/supplier-list.component';
import { OrderCreateComponent } from './components/order-create/order-create.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { WarehouseCreateComponent } from './components/warehouse-create/warehouse-create.component';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'suppliers', component: SupplierListComponent },
  { path: 'orders', component: OrderListComponent },
  { path: 'create-order', component: OrderCreateComponent },
  { path: 'create-warehouse', component: WarehouseCreateComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }