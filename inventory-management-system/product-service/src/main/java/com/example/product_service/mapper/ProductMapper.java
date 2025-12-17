package com.example.product_service.mapper;

import com.example.product_service.dto.ProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .sku(dto.getSku())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .category(dto.getCategory())
                .status(true) // Por defecto activo al crear
                .build();
    }

    public ProductResponseDTO toDTO(Product entity) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setSku(entity.getSku());
        dto.setPrice(entity.getPrice());
        dto.setStock(entity.getStock());
        dto.setCategory(entity.getCategory());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public void updateEntity(Product entity, ProductRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setCategory(dto.getCategory());
        // No actualizamos SKU ni Stock aquí usualmente, depende de la regla de negocio
    }
}