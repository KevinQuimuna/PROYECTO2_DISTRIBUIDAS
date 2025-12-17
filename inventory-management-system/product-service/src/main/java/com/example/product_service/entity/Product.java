package com.example.product_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false, unique = true)
    private String sku; // Código único de producto

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock; // Stock lógico global (opcional en este servicio, el real está en inventario)

    private String category;

    private Boolean status; // Activo / Inactivo
}