export interface Product {
  id?: number;
  name: string;
  sku: string;
  description?: string;
  price: number;
  stock?: number; // Stock lógico global
  category?: string;
  status?: boolean;
}

export interface Supplier {
  id?: number;
  name: string;
  email: string;
  phoneNumber?: string;
  address?: string;
  active?: boolean;
}

export interface Warehouse {
  id?: number;
  name: string;
  location?: string;
}

export interface Inventory {
  id?: number;
  sku: string;
  quantity: number;
  warehouseName?: string;
}

// Modelos para Órdenes de Compra
export interface PurchaseOrderItem {
  productId: number;
  quantity: number;
  sku?: string; // Para mostrar en tabla
  unitPrice?: number; // Para mostrar
  subTotal?: number; // Para mostrar
}

export interface PurchaseOrderRequest {
  supplierId: number;
  warehouseId: number;
  notes: string;
  items: PurchaseOrderItem[];
}

export interface PurchaseOrderResponse {
  id: number;
  supplierId: number;
  warehouseId: number;
  createdAt: string;
  status: string;
  totalAmount: number;
  items: any[];
}