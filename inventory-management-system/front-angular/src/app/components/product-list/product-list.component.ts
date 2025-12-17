import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api-service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
  standalone: false
})
export class ProductListComponent implements OnInit {
  products: any[] = [];
  filteredProducts: any[] = [];
  searchTerm: string = '';

  // Asegúrate de que este objeto tenga 'stock'
  newProduct = { 
    name: '', 
    sku: '', 
    price: 0, 
    stock: 0, // <--- AQUÍ SE GUARDA EL DATO DEL INPUT
    description: 'Producto General', 
    category: 'General' 
  };

  constructor(private api: ApiService) { }

  ngOnInit(): void { this.loadProducts(); }

  loadProducts() {
    this.api.getProducts().subscribe(data => {
      this.products = data;
      this.filteredProducts = data;
    });
  }

  filter() {
    const term = this.searchTerm.toLowerCase();
    this.filteredProducts = this.products.filter(p => 
      p.name.toLowerCase().includes(term) || p.sku.toLowerCase().includes(term)
    );
  }

  saveProduct() {
    // Validar que no envíe vacíos
    if(this.newProduct.name && this.newProduct.sku) {
        this.api.createProduct(this.newProduct).subscribe(() => {
            alert('Producto Guardado Correctamente');
            this.loadProducts();
            // Resetear formulario
            this.newProduct = { name: '', sku: '', price: 0, stock: 0, description: 'Producto General', category: 'General' };
        }, err => alert('Error al guardar. Revisa que el SKU no esté repetido.'));
    } else {
        alert('Por favor llena el Nombre y el SKU');
    }
  }
}