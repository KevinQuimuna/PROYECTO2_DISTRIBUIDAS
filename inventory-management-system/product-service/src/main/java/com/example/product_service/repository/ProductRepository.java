package com.example.product_service.repository;

import com.example.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Devuelve el producto completo buscando por SKU
    Optional<Product> findBySku(String sku);

    // Verifica existencia (usado al crear para evitar duplicados)
    boolean existsBySku(String sku);
}